package com.exampleapp.mvpapp_kotlin.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.exampleapp.mvpapp_kotlin.entity.Note

@Database(entities = [Note::class], version = 1, exportSchema = false)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun dao(): NotesDao
}
