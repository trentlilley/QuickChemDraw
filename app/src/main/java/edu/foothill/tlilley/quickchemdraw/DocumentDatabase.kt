package edu.foothill.tlilley.quickchemdraw

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * DocumentDatabase.kt
 * Final Project / CS 64A
 * Foothill College / R.Scovil
 * Trent Lilley / trent.vanlilley@gmail.com
 */

// database class singleton
@Database(entities = [Document::class], version = 1, exportSchema = false)
abstract class DocumentDatabase: RoomDatabase(){
    abstract fun documentDao(): DocumentDao

    // generate a new instance if there isn't one already, don't generate if already instantiated
    companion object {
        @Volatile
        private var INSTANCE: DocumentDatabase? = null

        fun getDatabase(context: Context): DocumentDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(context.applicationContext, DocumentDatabase::class.java, "document_database").build()
                INSTANCE = instance
                return instance
            }
        }
    }
}