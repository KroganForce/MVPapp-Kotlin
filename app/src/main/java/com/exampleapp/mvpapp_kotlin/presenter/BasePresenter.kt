package com.exampleapp.mvpapp_kotlin.presenter

interface BasePresenter<T> {
    fun attachView(view: T)
    fun detachView()
}