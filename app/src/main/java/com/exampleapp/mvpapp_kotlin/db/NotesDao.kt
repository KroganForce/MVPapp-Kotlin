package com.exampleapp.mvpapp_kotlin.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.exampleapp.mvpapp_kotlin.entity.Note
@Dao
interface NotesDao {
    @Insert
    fun insert(item: Note)

    @Update
    fun update(item: Note)

    @Query("SELECT * FROM NOTE")
    fun getData(): LiveData<List<Note>>

    @Query("SELECT note FROM NOTE WHERE id =:id")
    fun getDataById(id: Int): String

    @Query("DELETE FROM NOTE WHERE id = :id")
    fun deleteNote(id: Int)
}