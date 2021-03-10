package com.exampleapp.mvpapp_kotlin.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.exampleapp.mvpapp_kotlin.db.NotesDatabase
import com.exampleapp.mvpapp_kotlin.utils.Const
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Singleton
    @Provides
    fun createDb(context: Context): NotesDatabase =
        Room.databaseBuilder(context, NotesDatabase::class.java, Const.NOTES_DATABASE.name)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    db.execSQL("INSERT INTO NOTE(id,note) VALUES (123,'NOTE 1'),(124,'NOTE 2')")
                }
            }).build()


    @Singleton
    @Provides
    fun providesDao(db: NotesDatabase) = db.dao()
}