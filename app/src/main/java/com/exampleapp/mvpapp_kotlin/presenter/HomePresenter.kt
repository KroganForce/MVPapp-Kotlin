package com.exampleapp.mvpapp_kotlin.presenter

import androidx.lifecycle.LifecycleOwner
import com.exampleapp.mvpapp_kotlin.contract.HomeContract
import com.exampleapp.mvpapp_kotlin.repository.NoteRepository
import javax.inject.Inject

class HomePresenter @Inject constructor(private val repository: NoteRepository) :
    BasePresenter<HomeContract> {

    @set:Inject
    var homeView: HomeContract? = null


    override fun attachView(view: HomeContract) {
        homeView = view
    }

    override fun detachView() {
        homeView = null
    }

    fun viewIsReady(owner: LifecycleOwner) {
        repository.getAllData().observe(owner) { homeView?.setData(it) }
    }

    fun deleteNote(id: Int) = repository.deleteNote(id)
}
