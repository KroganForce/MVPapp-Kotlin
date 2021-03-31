package com.exampleapp.mvpapp_kotlin.contract

interface DetailContract {
    fun getNoteData(): String
    fun getNoteId(): Int
    fun setNoteText(text: String)
}
