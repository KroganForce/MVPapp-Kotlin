package com.exampleapp.mvpapp_kotlin.data

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import com.exampleapp.mvpapp_kotlin.TEST_EMPTY_STRING
import com.exampleapp.mvpapp_kotlin.TEST_ID
import com.exampleapp.mvpapp_kotlin.TEST_STRING
import com.exampleapp.mvpapp_kotlin.db.NotesDao
import com.exampleapp.mvpapp_kotlin.db.NotesDatabase
import com.exampleapp.mvpapp_kotlin.entity.Note
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.*
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
internal class NoteRepositoryTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: NotesDatabase
    private lateinit var dao: NotesDao

    @Before
    fun initDataBase() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NotesDatabase::class.java
        ).allowMainThreadQueries().build()
        dao = database.dao()
    }

    @After
    fun closeDataBase() {
        database.close()
    }

    @Test
    fun getDataTest() = runTest {
        val note = Note(TEST_ID, TEST_STRING)
        dao.insert(note)
        val result = dao.getData().first().toList()
        Assert.assertEquals(note.id, result.first().id)
        Assert.assertEquals(TEST_STRING, result.first().text)
    }

    @Test
    fun getDataById() = runBlocking {
        val note = Note(TEST_ID, TEST_STRING)
        dao.insert(note)
        val result = dao.getDataById(1).first()?.text
        Assert.assertEquals(TEST_STRING, result)
    }

    @Test
    fun insertTest() = runTest {
        val note = Note(TEST_ID, TEST_STRING)
        dao.insert(note)
        val result = dao.getData().first().toList()
        Assert.assertEquals(note.id, result.first().id)
        Assert.assertEquals(TEST_STRING, result.first().text)
    }

    @Test
    fun updateTest() = runTest {
        val firstNote = Note(TEST_ID, TEST_EMPTY_STRING)
        dao.insert(firstNote)
        val secondNote = Note(TEST_ID, TEST_STRING)
        dao.update(secondNote)
        val result = dao.getData().first().toList()
        Assert.assertEquals(secondNote.id, result.first().id)
        Assert.assertEquals(TEST_STRING, result.first().text)
    }

    @Test
    fun deleteTest() = runTest {
        val firstNote = Note(TEST_ID, TEST_EMPTY_STRING)
        dao.insert(firstNote)
        dao.deleteNote(firstNote.id)
        val result = dao.getDataById(firstNote.id).first()
        Assert.assertEquals(null, result)
    }
}
