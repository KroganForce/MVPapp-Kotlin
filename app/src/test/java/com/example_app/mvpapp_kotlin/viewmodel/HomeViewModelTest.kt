package com.example_app.mvpapp_kotlin.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example_app.mvpapp_kotlin.entity.Note
import com.example_app.mvpapp_kotlin.repository.NoteRepository
import com.example_app.mvpapp_kotlin.TEST_ID
import com.example_app.mvpapp_kotlin.TEST_STRING
import com.example_app.mvpapp_kotlin.utilities.MainCoroutineRule
import com.example_app.mvpapp_kotlin.utilities.getOrAwaitValue
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
class HomeViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var homeViewModel: HomeViewModel
    private val noteRepository: NoteRepository = mockk()

    @Test
    fun getAllNoteDataTest() {
        coEvery { noteRepository.getAllData() } returns flow {
            emit(listOf(Note(TEST_ID, TEST_STRING)))
        }
        homeViewModel = HomeViewModel(noteRepository)
        assertNotNull(homeViewModel.allNotes.getOrAwaitValue())
        assertEquals(homeViewModel.allNotes.getOrAwaitValue()[0].id, TEST_ID)
        assertEquals(homeViewModel.allNotes.getOrAwaitValue()[0].text, TEST_STRING)
    }

    @Test
    fun deleteNoteTest() {
        coEvery { noteRepository.getAllData() } returns flow {
            emit(emptyList<Note>())
        }
        coEvery { noteRepository.deleteNote(TEST_ID) } answers {}
        val list = mutableListOf(Note(TEST_ID, TEST_STRING))
        homeViewModel = HomeViewModel(noteRepository)
        homeViewModel.allNotes.value = list
        homeViewModel.deleteNote(TEST_ID)
        coVerify {
            noteRepository.getAllData()
            noteRepository.deleteNote(TEST_ID)
        }
        assertEquals(emptyList<Note>(), homeViewModel.allNotes.getOrAwaitValue())
    }
}
