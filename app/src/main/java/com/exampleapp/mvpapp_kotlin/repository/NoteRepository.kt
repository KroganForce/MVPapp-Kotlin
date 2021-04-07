package com.exampleapp.mvpapp_kotlin.repository

import com.exampleapp.mvpapp_kotlin.db.NotesDao
import com.exampleapp.mvpapp_kotlin.entity.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NoteRepository @Inject constructor(
    private val dao: NotesDao
) {

    fun getAllData() = dao.getData()

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

    fun getDataById(id: Int): Flow<String?> {
        return dao.getDataById(id)
    }
}
