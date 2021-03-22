package com.exampleapp.mvpapp_kotlin.di.module

import com.exampleapp.mvpapp_kotlin.di.annotation.FragmentScope
import com.exampleapp.mvpapp_kotlin.ui.DetailFragment
import com.exampleapp.mvpapp_kotlin.ui.HomeFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
interface MainActivityModule {
    @FragmentScope
    @ContributesAndroidInjector(modules = [RepositoryModule::class, FragmentModule::class])
    fun injectHomeFragment(): HomeFragment

    @FragmentScope
    @ContributesAndroidInjector(modules = [RepositoryModule::class, FragmentModule::class])
    fun injectDetailFragment(): DetailFragment
}
