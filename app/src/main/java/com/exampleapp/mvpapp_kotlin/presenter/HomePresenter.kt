package com.exampleapp.mvpapp_kotlin.presenter

import androidx.lifecycle.LiveData
import com.exampleapp.mvpapp_kotlin.contract.HomeContract
import com.exampleapp.mvpapp_kotlin.entity.Note
import com.exampleapp.mvpapp_kotlin.repository.NoteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomePresenter @Inject constructor(private val repository: NoteRepository) :
    BasePresenter<HomeContract>() {

    val allNotes: LiveData<List<Note>> = repository.getAllData()

    fun viewIsReady() {
        view?.setData()
    }

    fun deleteNote(id: Int) {
        scope.launch {
            repository.deleteNote(id)
        }
    }
}
