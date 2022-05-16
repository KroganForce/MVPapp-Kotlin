package com.exampleapp.mvpapp_kotlin.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.exampleapp.mvpapp_kotlin.entity.Note
import com.exampleapp.mvpapp_kotlin.repository.NoteRepository
import com.exampleapp.mvpapp_kotlin.TEST_ID
import com.exampleapp.mvpapp_kotlin.TEST_STRING
import com.exampleapp.mvpapp_kotlin.utilities.MainCoroutineRule
import com.exampleapp.mvpapp_kotlin.utilities.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class HomePresenterTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var homePresenter: HomePresenter
    private val noteRepository: NoteRepository = mockk()

    @Test
    fun getAllNoteDataTest() {
        coEvery { noteRepository.getAllData() } returns flow {
            emit(listOf(Note(TEST_ID, TEST_STRING)))
        }
        homePresenter = HomePresenter(noteRepository)
        assertNotNull(homePresenter.allNotes.getOrAwaitValue())
        assertEquals(homePresenter.allNotes.getOrAwaitValue()[0].id, TEST_ID)
        assertEquals(homePresenter.allNotes.getOrAwaitValue()[0].text, TEST_STRING)
    }

    @Test
    fun deleteNoteTest() {
        coEvery { noteRepository.getAllData() } returns flow {
            emit(emptyList<Note>())
        }
        coEvery { noteRepository.deleteNote(TEST_ID) } answers {}
        val list = mutableListOf(Note(TEST_ID, TEST_STRING))
        homePresenter = HomePresenter(noteRepository)
        homePresenter.allNotes.value = list
        homePresenter.deleteNote(TEST_ID)
        coVerify {
            noteRepository.getAllData()
            noteRepository.deleteNote(TEST_ID)
        }
        assertEquals(emptyList<Note>(), homePresenter.allNotes.getOrAwaitValue())
    }
}
