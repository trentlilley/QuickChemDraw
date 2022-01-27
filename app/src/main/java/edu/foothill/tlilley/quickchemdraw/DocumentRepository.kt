package edu.foothill.tlilley.quickchemdraw

import androidx.lifecycle.LiveData

/**
 * DocumentRepository.kt
 * Final Project / CS 64A
 * Foothill College / R.Scovil
 * Trent Lilley / trent.vanlilley@gmail.com
 */

// provides access to DAO functions
class DocumentRepository(private val documentDao: DocumentDao) {
    val readAllData: LiveData<List<Document>> = documentDao.readAllData()

    suspend fun addDocument(document: Document) {
        documentDao.addDocument(document)
    }

    suspend fun updateDocument(document: Document) {
        documentDao.updateDocument(document)
    }
}