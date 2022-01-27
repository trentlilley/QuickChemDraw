package edu.foothill.tlilley.quickchemdraw

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * DocumentDao.kt
 * Final Project / CS 64A
 * Foothill College / R.Scovil
 * Trent Lilley / trent.vanlilley@gmail.com
 */

// interface to interact with room database
@Dao
interface DocumentDao {

    @Insert
    suspend fun addDocument(document: Document)

    @Query("SELECT * FROM document_table ORDER BY title ASC")
    fun readAllData(): LiveData<List<Document>>

    @Update
    suspend fun updateDocument(document: Document)

}