package com.exampleapp.mvpapp_kotlin.data

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.MutableLiveData
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.filters.SmallTest
import com.exampleapp.mvpapp_kotlin.TEST_ID
import com.exampleapp.mvpapp_kotlin.TEST_STRING
import com.exampleapp.mvpapp_kotlin.db.NotesDatabase
import com.exampleapp.mvpapp_kotlin.entity.Note
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class DaoTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var database: NotesDatabase

    @Before
    fun initDataBase() {
        database = Room.inMemoryDatabaseBuilder(
            getApplicationContext(),
            NotesDatabase::class.java
        ).build()
    }

    @After
    fun closeDataBase() = database.close()

    @Test
    fun insertTest() = runBlockingTest {
        val note = Note(TEST_ID, TEST_STRING)
        database.dao().insert(note)
        database.dao().getDataById(TEST_ID).collect {
            assertEquals(TEST_STRING, it)
        }
    }
}
