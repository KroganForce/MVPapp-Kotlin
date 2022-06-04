package com.exampleapp.mvpapp_kotlin.presenter

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.exampleapp.mvpapp_kotlin.entity.Note
import com.exampleapp.mvpapp_kotlin.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomePresenter @Inject constructor(
    private val noteRepository: NoteRepository
) : ViewModel() {

    val allNotes: MutableLiveData<List<Note>> =
        noteRepository.getAllData().asLiveData() as MutableLiveData<List<Note>>

    fun deleteNote(id: Int) {
        viewModelScope.launch {
            noteRepository.deleteNote(id)
        }
    }
}
