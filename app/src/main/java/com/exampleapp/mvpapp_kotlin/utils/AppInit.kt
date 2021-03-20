package com.exampleapp.mvpapp_kotlin.utils


import com.exampleapp.mvpapp_kotlin.di.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class AppInit : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerApplicationComponent.builder().context(this).buildComponent()
    }
}
