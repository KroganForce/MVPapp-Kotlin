package com.example_app.mvpapp_kotlin.di.module

import com.example_app.mvpapp_kotlin.db.NotesDao
import com.example_app.mvpapp_kotlin.repository.NoteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class RepositoryModule {
    @Provides
    fun provideRepository(dao: NotesDao): NoteRepository {
        return NoteRepository(dao)
    }
}
