package com.exampleapp.mvpapp_kotlin.presenter

import androidx.lifecycle.MutableLiveData
import com.exampleapp.mvpapp_kotlin.contract.HomeContract
import com.exampleapp.mvpapp_kotlin.entity.Note
import com.exampleapp.mvpapp_kotlin.repository.NoteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomePresenter @Inject constructor(private val repository: NoteRepository) :
    BasePresenter<HomeContract>() {

    val liveData = MutableLiveData<List<Note>>()

    fun viewIsReady() {
        scope.launch {
            liveData.postValue(repository.getAllData())
        }
    }

    fun deleteNote(id: Int) {
        scope.launch {
            repository.deleteNote(id)
        }
    }
}
