package com.exampleapp.mvpapp_kotlin.contract

import com.exampleapp.mvpapp_kotlin.entity.Note

interface HomeContract {
    fun setData(list: List<Note>)
}
