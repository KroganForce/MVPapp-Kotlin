package com.example_app.mvpapp_kotlin.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example_app.mvpapp_kotlin.entity.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun dao(): NotesDao
}
