package com.exampleapp.mvpapp_kotlin.di

import android.content.Context
import com.exampleapp.mvpapp_kotlin.di.module.AppModule
import com.exampleapp.mvpapp_kotlin.di.module.RoomModule
import com.exampleapp.mvpapp_kotlin.utils.AppInit
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    // TODO: 23.06.2021 init Note immediately as an argument
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        RoomModule::class]
)
interface ApplicationComponent : AndroidInjector<AppInit> {

    override fun inject(appInit: AppInit)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun buildComponent(): ApplicationComponent
    }
}
