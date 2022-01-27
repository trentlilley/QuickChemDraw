package edu.foothill.tlilley.quickchemdraw

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import kotlinx.android.synthetic.main.fragment_draw.*
import kotlinx.android.synthetic.main.fragment_draw.view.*
import java.util.*

/**
 * DrawFragment.kt
 * Final Project / CS 64A
 * Foothill College / R.Scovil
 * Trent Lilley / trent.vanlilley@gmail.com
 */

const val DEFAULT_BRUSH_SIZE = 7f
const val DEFAULT_ERASER_SIZE = 40f

// manages fragment_draw layout which handles all of the user's drawing activities
class DrawFragment : Fragment(), View.OnClickListener {

    private lateinit var navController: NavController
    private lateinit var documentViewModel: DocumentViewModel


    // inflates the fragment_draw layout
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        documentViewModel = ViewModelProvider(this).get(DocumentViewModel::class.java)
        val view = inflater.inflate(R.layout.fragment_draw, container, false)

        return view
    }

    // initializes the navigation controller used to navigate between fragments
    // sets onClickListener for all buttons in this layout
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        val drawingButtons = listOf<View>(btnEraser, btnBlack, btnRed, btnBlue, btnGreen, fabDone)
        drawingButtons.forEach{it.setOnClickListener(this)}
    }

    // defines what each button in the layout does
    override fun onClick(v: View?) {

        when(v?.id) {
            R.id.fabDone -> {
                insertDataToDatabase()
            }
            R.id.btnEraser -> {
                drawView.setErase(true)
                drawView.setBrushThickness(DEFAULT_ERASER_SIZE)
            }
            R.id.btnBlack -> {
                drawView.setColor(R.color.black)
                drawView.setErase(false)
                drawView.setBrushThickness(DEFAULT_BRUSH_SIZE)
            }
            R.id.btnRed -> {
                drawView.setColor(R.color.red)
                drawView.setErase(false)
                drawView.setBrushThickness(DEFAULT_BRUSH_SIZE)
            }
            R.id.btnBlue -> {
                drawView.setColor(R.color.blue)
                drawView.setErase(false)
                drawView.setBrushThickness(DEFAULT_BRUSH_SIZE)
            }
            R.id.btnGreen -> {
                drawView.setColor(R.color.green)
                drawView.setErase(false)
                drawView.setBrushThickness(DEFAULT_BRUSH_SIZE)
            }
        }
    }

    // create a new Document object to store information of our canvas to re-create it later
    // store title text, paths, and assign id
    private fun insertDataToDatabase() {
        val title = etDocTitle.text.toString()
        val id = UUID.randomUUID().toString().take(8)

        // if the title is empty, don't save, prompt user to enter title
        if (title.isNotEmpty()) {
            val newDocument = Document(id, title)
            documentViewModel.addDocument(newDocument)
            Toast.makeText(requireContext(), "Canvas Saved", Toast.LENGTH_LONG).show()
            navController!!.navigate(R.id.action_drawFragment_to_listFragment)
        }
        else {
            Toast.makeText(requireContext(), "Please enter a title for this document", Toast.LENGTH_LONG).show()
        }
    }
}

