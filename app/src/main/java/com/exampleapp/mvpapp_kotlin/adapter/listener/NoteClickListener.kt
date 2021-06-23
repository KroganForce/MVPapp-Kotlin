package com.exampleapp.mvpapp_kotlin.adapter.listener

interface NoteClickListener {
    fun clickOnNote(id: Int)
    fun deleteItem(id: Int)
}
