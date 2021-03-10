package com.exampleapp.mvpapp_kotlin.presenter

import com.exampleapp.mvpapp_kotlin.contract.DetailContract
import com.exampleapp.mvpapp_kotlin.repository.NoteRepository
import javax.inject.Inject

class DetailPresenter @Inject constructor(private val repository: NoteRepository) :
    BasePresenter<DetailContract> {

    @set:Inject
    var detailView: DetailContract? = null

    override fun attachView(view: DetailContract) {
        detailView = view
    }

    fun add() {
        detailView?.getNoteData()?.let { repository.addNote(it) }
    }

    fun update(id: Int) {
        detailView?.getNoteData()?.let { repository.updateNote(id, it) }
    }

    fun getNoteData(): String = repository.getDataById(id = detailView?.getNoteId() ?: -1)


    override fun detachView() {
        detailView = null
    }
}