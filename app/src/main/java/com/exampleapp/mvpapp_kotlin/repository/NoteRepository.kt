package com.exampleapp.mvpapp_kotlin.repository

import androidx.lifecycle.LiveData
import com.exampleapp.mvpapp_kotlin.utils.Const.*
import com.exampleapp.mvpapp_kotlin.db.NotesDao
import com.exampleapp.mvpapp_kotlin.entity.Note
import com.exampleapp.mvpapp_kotlin.utils.returningDataExecutor
import com.exampleapp.mvpapp_kotlin.utils.runExecutor
import javax.inject.Inject

class NoteRepository @Inject constructor(val dao: NotesDao) {

    fun getAllData(): LiveData<List<Note>> = dao.getData()

    fun addNote(text: String) {
        val note = Note(text = text)
        dao.runExecutor(INSERT, note)
    }

    fun updateNote(id: Int, text: String) {
        val note = Note(id, text)
        dao.runExecutor(UPDATE, note)
    }

    fun deleteNote(id: Int) {
        val note = Note(id)
        dao.runExecutor(DELETE, note)
    }

    fun getDataById(id: Int): String {
        return dao.returningDataExecutor(id) ?: EMPTY_STRING.name
    }
}