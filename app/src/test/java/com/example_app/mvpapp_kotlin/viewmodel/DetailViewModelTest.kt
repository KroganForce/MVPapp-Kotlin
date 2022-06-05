package com.example_app.mvpapp_kotlin.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.example_app.mvpapp_kotlin.*
import com.example_app.mvpapp_kotlin.entity.Note
import com.example_app.mvpapp_kotlin.repository.NoteRepository
import com.example_app.mvpapp_kotlin.utilities.MainCoroutineRule
import com.example_app.mvpapp_kotlin.utilities.getOrAwaitValue
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class DetailViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var detailViewModel: DetailViewModel
    private val noteRepository: NoteRepository = mockk()

    @Before
    fun init() {
        detailViewModel = DetailViewModel(noteRepository)
    }

    @Test
    fun getNoteByIdTest() {
        coEvery { noteRepository.getDataById(TEST_ID) } returns flow {
            emit(Note(TEST_ID, TEST_STRING))
        }
        val result = detailViewModel.getNoteById(TEST_ID).getOrAwaitValue()
        assertNotNull(result)
        assertEquals(TEST_STRING, result?.text)
    }

    @Test
    fun addTest() {
        coEvery { noteRepository.getDataById(TEST_ID) } returns flow {
            emit(Note(TEST_ID, TEST_STRING))
        }
        coEvery { noteRepository.addNote(TEST_STRING) } answers {}
        detailViewModel.add(TEST_STRING)

        assertEquals(
            TEST_STRING,
            noteRepository.getDataById(TEST_ID).asLiveData().getOrAwaitValue()?.text
        )

        coVerify {
            noteRepository.getDataById(TEST_ID)
            noteRepository.addNote(TEST_STRING)
        }
    }

    @Test
    fun updateTest() {
        coEvery { noteRepository.getDataById(TEST_ID) } returns flow {
            emit(Note(TEST_ID, TEST_STRING))
        }
        coEvery { noteRepository.updateNote(TEST_ID, TEST_STRING) } answers {}
        detailViewModel.update(TEST_ID, TEST_STRING)

        assertEquals(
            TEST_STRING,
            noteRepository.getDataById(TEST_ID).asLiveData().getOrAwaitValue()?.text
        )

        coVerify {
            noteRepository.getDataById(TEST_ID)
            noteRepository.updateNote(TEST_ID, TEST_STRING)
        }
    }

    @Test
    fun insertOrUpdateTest() {
        coEvery { noteRepository.addNote(TEST_STRING) } answers {
            TEST_STRING
        }
        coEvery { noteRepository.updateNote(TEST_ID, TEST_STRING) } answers {
            TEST_STRING
        }
        detailViewModel.insertOrUpdate(TEST_ID, TEST_STRING)
        coVerify {
            noteRepository.updateNote(TEST_ID, TEST_STRING)
        }
        detailViewModel.insertOrUpdate(TEST_EMPTY_ID, TEST_STRING)
        coVerify {
            noteRepository.addNote(TEST_STRING)
        }
    }

    @Test
    fun checkingWhiteSpacesTest() {
        assertEquals(true, detailViewModel.checkingWhiteSpaces(TEST_EMPTY_STRING.length))
        assertEquals(false, detailViewModel.checkingWhiteSpaces(TEST_STRING.length))
    }
}
