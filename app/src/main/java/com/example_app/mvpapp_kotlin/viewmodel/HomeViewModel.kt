package com.example_app.mvpapp_kotlin.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example_app.mvpapp_kotlin.entity.Note
import com.example_app.mvpapp_kotlin.repository.NoteRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
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
