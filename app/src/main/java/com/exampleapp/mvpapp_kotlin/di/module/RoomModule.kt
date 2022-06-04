package com.exampleapp.mvpapp_kotlin.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.exampleapp.mvpapp_kotlin.db.NotesDatabase
import com.exampleapp.mvpapp_kotlin.utils.NOTES_DATABASE
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RoomModule {

    @Provides
    fun createDb(@ApplicationContext context: Context): NotesDatabase =
        Room.databaseBuilder(context, NotesDatabase::class.java, NOTES_DATABASE)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    db.execSQL("INSERT INTO NOTE(id,note) VALUES (123,'NOTE 1'),(124,'NOTE 2')")
                }
            }).build()

    @Provides
    fun providesDao(db: NotesDatabase) = db.dao()
}
