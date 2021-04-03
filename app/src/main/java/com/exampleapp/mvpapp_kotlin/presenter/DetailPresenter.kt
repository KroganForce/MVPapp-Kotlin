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

    private fun add(noteText: String) {
        scope.launch {
            noteRepository.addNote(noteText)
        }
    }

    private fun update(id: Int, noteText: String) {
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

    fun checkingWhiteSpaces(noteText: String, editTextLength: Int): Boolean {
        return noteText.contains(Regex("""\s"""))
                || editTextLength == 0
                || noteText.contains(Regex("""\s + \S"""))
    }
}
