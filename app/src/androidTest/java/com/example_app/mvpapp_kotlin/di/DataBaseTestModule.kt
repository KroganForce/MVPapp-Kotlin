package com.example_app.mvpapp_kotlin.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example_app.mvpapp_kotlin.db.NotesDatabase
import com.example_app.mvpapp_kotlin.di.module.RoomModule
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RoomModule::class]
)
class DataBaseTestModule {

    @Singleton
    @Provides
    fun provideDataBase(@ApplicationContext context: Context): NotesDatabase =
        Room.inMemoryDatabaseBuilder(context, NotesDatabase::class.java)
            .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    db.execSQL("INSERT INTO NOTE(id,note) VALUES (1,'TEST NOTE 1'),(2,'TEST NOTE 2')")
                }
            }).allowMainThreadQueries().build()

    @Provides
    fun providesDao(db: NotesDatabase) = db.dao()
}
