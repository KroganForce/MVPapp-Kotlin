package com.exampleapp.mvpapp_kotlin.db

import androidx.room.*
import com.exampleapp.mvpapp_kotlin.entity.Note
import kotlinx.coroutines.flow.Flow

@Dao
interface NotesDao {

    @Query("SELECT * FROM NOTE")
    fun getData(): Flow<List<Note>>

    @Query("SELECT note FROM NOTE WHERE id =:id")
    fun getDataById(id: Int): Flow<String?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(item: Note)

    @Update
    suspend fun update(item: Note)

    @Query("DELETE FROM NOTE WHERE id = :id")
    suspend fun deleteNote(id: Int)
    //TODO("Add Flow in Room, remove Enum constants.")
}
