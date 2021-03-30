package com.exampleapp.mvpapp_kotlin.db

import androidx.room.*
import com.exampleapp.mvpapp_kotlin.entity.Note

@Dao
interface NotesDao {

    @Query("SELECT * FROM NOTE")
    fun getData(): List<Note>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Note)

    @Update
    suspend fun update(item: Note)

    @Query("SELECT note FROM NOTE WHERE id =:id")
    suspend fun getDataById(id: Int): String

    @Query("DELETE FROM NOTE WHERE id = :id")
    suspend fun deleteNote(id: Int)
}
