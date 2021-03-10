package com.exampleapp.mvpapp_kotlin.di.module

import com.exampleapp.mvpapp_kotlin.contract.DetailContract
import com.exampleapp.mvpapp_kotlin.contract.HomeContract
import com.exampleapp.mvpapp_kotlin.ui.DetailFragment
import com.exampleapp.mvpapp_kotlin.ui.HomeFragment
import dagger.Binds
import dagger.Module

@Module
interface FragmentModule {
    @Binds
    fun contractHome(fragment: HomeFragment): HomeContract

    @Binds
    fun contractDetail(fragment: DetailFragment): DetailContract
}