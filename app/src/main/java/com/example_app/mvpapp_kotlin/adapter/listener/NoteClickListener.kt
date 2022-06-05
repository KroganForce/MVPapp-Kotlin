package com.example_app.mvpapp_kotlin.adapter.listener

interface NoteClickListener {
    fun clickOnNote(id: Int)
    fun deleteItem(id: Int)
}
