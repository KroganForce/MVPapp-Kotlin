package com.exampleapp.mvpapp_kotlin.presenter

import androidx.lifecycle.MutableLiveData
import com.exampleapp.mvpapp_kotlin.contract.DetailContract
import com.exampleapp.mvpapp_kotlin.repository.NoteRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val repository: NoteRepository) :
    BasePresenter<DetailContract>() {

    val liveData = MutableLiveData<String>()

    fun add() {
        scope.launch {
            view?.getNoteData()?.let {
                repository.addNote(it)
            }
        }
    }

    fun update(id: Int) {
        scope.launch {
            view?.getNoteData()?.let {
                repository.updateNote(id, it)
            }
        }
    }

    fun getNoteData() {
        scope.launch {
            liveData.postValue(repository.getDataById(view?.getNoteId() ?: -1))
        }
    }
}
