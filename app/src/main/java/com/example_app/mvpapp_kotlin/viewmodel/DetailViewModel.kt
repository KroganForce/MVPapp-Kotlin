package com.example_app.mvpapp_kotlin.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example_app.mvpapp_kotlin.entity.Note
import com.example_app.mvpapp_kotlin.repository.NoteRepository
import com.example_app.mvpapp_kotlin.utils.EMPTY_ID
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    fun getNoteById(id: Int): LiveData<Note?> {
        return noteRepository.getDataById(id).asLiveData()

    }

    fun add(noteText: String) {
        viewModelScope.launch {
            noteRepository.addNote(noteText)
        }
    }

    fun update(id: Int, noteText: String) {
        viewModelScope.launch {
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
