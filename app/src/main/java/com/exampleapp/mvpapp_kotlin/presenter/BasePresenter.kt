package com.exampleapp.mvpapp_kotlin.presenter

import androidx.annotation.CallSuper
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel

abstract class BasePresenter(
    val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.IO)
) {

    @CallSuper
    fun detachView() {
        scope.cancel()
    }
}
