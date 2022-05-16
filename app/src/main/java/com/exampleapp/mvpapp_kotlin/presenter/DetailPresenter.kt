package com.exampleapp.mvpapp_kotlin.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.exampleapp.mvpapp_kotlin.repository.NoteRepository
import com.exampleapp.mvpapp_kotlin.utils.EMPTY_ID
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailPresenter @Inject constructor(
    private val noteRepository: NoteRepository
) : BasePresenter() {

    fun getNoteById(id: Int): LiveData<String?> {
        return noteRepository.getDataById(id).asLiveData()
    }

    fun add(noteText: String) {
        scope.launch {
            noteRepository.addNote(noteText)
        }
    }

    fun update(id: Int, noteText: String) {
        scope.launch {
            noteRepository.updateNote(id, noteText)
        }
    }

    fun insertOrUpdate(noteId: Int, noteText: String) {
        if (noteId == EMPTY_ID)
            add(noteText)
        else
            update(noteId, noteText)
    }

    fun checkingWhiteSpaces(editTextLength: Int): Boolean {
        return editTextLength == 0
    }
}
