package edu.foothill.tlilley.quickchemdraw

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.util.*
/**
 * Document.kt
 * Final Project / CS 64A
 * Foothill College / R.Scovil
 * Trent Lilley / trent.vanlilley@gmail.com
 */

// a document consists of an id, title, and an array/list of paths recorded in DrawView
@Parcelize
@Entity(tableName = "document_table")
data class Document (@PrimaryKey val id: String, val title: String): Parcelable