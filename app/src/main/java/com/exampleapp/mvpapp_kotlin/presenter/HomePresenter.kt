package com.exampleapp.mvpapp_kotlin.presenter

import androidx.lifecycle.LiveData
import androidx.lifecycle.asLiveData
import com.exampleapp.mvpapp_kotlin.entity.Note
import com.exampleapp.mvpapp_kotlin.repository.NoteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomePresenter @Inject constructor(
    private val noteRepository: NoteRepository
) : BasePresenter() {

    val allNotes: LiveData<List<Note>> = noteRepository.getAllData().asLiveData()

    fun deleteNote(id: Int) {
        scope.launch {
            noteRepository.deleteNote(id)
        }
    }
}
