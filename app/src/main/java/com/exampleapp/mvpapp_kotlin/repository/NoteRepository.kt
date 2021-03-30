package com.exampleapp.mvpapp_kotlin.repository

import com.exampleapp.mvpapp_kotlin.db.NotesDao
import com.exampleapp.mvpapp_kotlin.entity.Note
import javax.inject.Inject

class NoteRepository @Inject constructor(private val dao: NotesDao) {

    fun getAllData(): List<Note> = dao.getData()

    suspend fun addNote(text: String) {
        val note = Note(text = text)
        dao.insert(note)
    }

    suspend fun updateNote(id: Int, text: String) {
        val note = Note(id, text)
        dao.update(note)
    }

    suspend fun deleteNote(id: Int) {
        dao.deleteNote(id)
    }

    suspend fun getDataById(id: Int): String {
        return dao.getDataById(id)
    }
}
