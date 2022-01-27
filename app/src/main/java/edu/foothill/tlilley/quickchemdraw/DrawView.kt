package edu.foothill.tlilley.quickchemdraw

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Parcelable
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.res.ResourcesCompat
import java.io.ByteArrayOutputStream
import java.nio.ByteBuffer

/**
 * DrawView.kt
 * Final Project / CS 64A
 * Foothill College / R.Scovil
 * Trent Lilley / trent.vanlilley@gmail.com
 */

// custom view that constructs the drawing canvas
class DrawView(context: Context, attrs: AttributeSet? = null): View(context, attrs) {

    // set up drawing tools
    private var path = Path()
    private var initialDrawColor = ResourcesCompat.getColor(resources, R.color.black, null)
    private lateinit var drawingCanvas: Canvas
    private lateinit var drawingBitmap: Bitmap
    private var brushThickness = 7f
    private var eraseIsOn = false
    // position variables to keep track of where the user is touching
    private var currentX = 0f
    private var currentY = 0f
    private var motionX = 0f
    private var motionY = 0f

    // paintbrush settings, color and thickness changed by buttons in DrawFragment class via setters
    @SuppressLint("ResourceAsColor")
    private var paint = Paint().apply {
        color = initialDrawColor
        isAntiAlias = true
        isDither = true
        style = Paint.Style.STROKE
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = brushThickness
    }

    // initializes the bitmap and canvas
    override fun onSizeChanged(width: Int, height: Int, oldWidth: Int, oldHeight: Int) {
        super.onSizeChanged(width, height, oldWidth, oldHeight)

        if (::drawingBitmap.isInitialized)
            drawingBitmap.recycle()

        drawingBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
        drawingCanvas = Canvas(drawingBitmap)
    }

    // handles drawing events
    override fun onDraw(canvas: Canvas) {
        canvas.drawBitmap(drawingBitmap, 0f, 0f, null)
    }


    // makes the canvas respond to user touch events
    override fun onTouchEvent(event: MotionEvent): Boolean {
        motionX = event.x
        motionY = event.y

        when (event.action) {
            // create a starting coordinate for the path when user first places finger on screen
            MotionEvent.ACTION_DOWN -> {
                path.reset()
                path.moveTo(motionX, motionY)
                currentX = motionX
                currentY = motionY
            }
            // track movement of user's finger, update coordinates
            MotionEvent.ACTION_MOVE -> {
                path.quadTo(currentX, currentY, (motionX + currentX) / 2, (motionY + currentY) / 2)
                currentX = motionX
                currentY = motionY
                // draws the path in the canvas
                drawingCanvas.drawPath(path, paint)
                invalidate()
            }

            // path is complete once user removes finger from screen
            // TODO: Record path by storing in list of path objects
            MotionEvent.ACTION_UP -> {
                path.reset()
            }
        }
        return true
    }

    // called by color buttons to change brush color
    fun setColor(newColor: Int) {
       paint.color = ResourcesCompat.getColor(resources, newColor, null)
    }

    // called by eraser button to change brush thickness and color buttons to reset brush thickness
    fun setBrushThickness(newThickness: Float) {
        paint.strokeWidth = newThickness
    }

    // toggles eraser: true passed when erase button pressed, false passed when any color button pressed
    fun setErase(isErase: Boolean) {
        eraseIsOn = isErase
        if (eraseIsOn)
            paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.CLEAR)
        else
            paint.xfermode = null
    }
}