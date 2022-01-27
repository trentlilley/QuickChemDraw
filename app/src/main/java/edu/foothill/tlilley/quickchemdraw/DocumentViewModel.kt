package edu.foothill.tlilley.quickchemdraw

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * DocumentViewModel.kt
 * Final Project / CS 64A
 * Foothill College / R.Scovil
 * Trent Lilley / trent.vanlilley@gmail.com
 */

// provides access to repository functions, runs database functions on background thread
class DocumentViewModel(application: Application) : AndroidViewModel(application) {
    val readAllData: LiveData<List<Document>>
    private val repository: DocumentRepository

    // generate instance of database class, access dao functions through the repository
    init {
        val documentDao = DocumentDatabase.getDatabase(application).documentDao()
        repository = DocumentRepository(documentDao)
        readAllData = repository.readAllData
    }

    fun addDocument(document: Document) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.addDocument(document)
        }
    }

    fun updateDocument(document: Document) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateDocument(document)
        }
    }
}