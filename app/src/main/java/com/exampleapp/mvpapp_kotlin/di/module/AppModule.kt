package com.exampleapp.mvpapp_kotlin.di.module

import com.exampleapp.mvpapp_kotlin.di.annotation.ActivityScope
import com.exampleapp.mvpapp_kotlin.ui.MainActivity
import dagger.Module
import dagger.android.AndroidInjectionModule
import dagger.android.ContributesAndroidInjector

@Module(includes = [AndroidInjectionModule::class])
interface AppModule {
    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    fun mainActivityInjector(): MainActivity
}
