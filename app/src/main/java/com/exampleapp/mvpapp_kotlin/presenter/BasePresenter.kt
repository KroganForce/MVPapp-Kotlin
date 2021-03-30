package com.exampleapp.mvpapp_kotlin.presenter

import androidx.annotation.CallSuper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class BasePresenter<T> {

    val scope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
    var view: T? = null

    @CallSuper
    fun attachView(view: T) {
        this.view = view
    }

    @CallSuper
    fun detachView() {
        this.view = null
        scope.cancel()
    }
}
