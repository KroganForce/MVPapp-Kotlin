package com.exampleapp.mvpapp_kotlin.di.module

import com.exampleapp.mvpapp_kotlin.db.NotesDao
import com.exampleapp.mvpapp_kotlin.repository.NoteRepository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
    @Provides
    fun provideRepository(dao: NotesDao): NoteRepository {
        return NoteRepository(dao)
    }
}