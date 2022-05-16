package com.exampleapp.mvpapp_kotlin.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.exampleapp.mvpapp_kotlin.*
import com.exampleapp.mvpapp_kotlin.repository.NoteRepository
import com.exampleapp.mvpapp_kotlin.utilities.MainCoroutineRule
import com.exampleapp.mvpapp_kotlin.utilities.getOrAwaitValue
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
class DetailPresenterTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var detailPresenter: DetailPresenter
    private val noteRepository: NoteRepository = mockk()

    @Before
    fun init() {
        detailPresenter = DetailPresenter(noteRepository)
    }

    @Test
    fun getNoteByIdTest() {
        coEvery { noteRepository.getDataById(TEST_ID) } returns flow {
            emit(TEST_STRING)
        }
        val result = detailPresenter.getNoteById(TEST_ID).getOrAwaitValue()
        assertNotNull(result)
        assertEquals(TEST_STRING, result)
    }

    @Test
    fun addTest() {
        coEvery { noteRepository.getDataById(TEST_ID) } returns flow {
            emit(TEST_STRING)
        }
        coEvery { noteRepository.addNote(TEST_STRING) } answers {}
        detailPresenter.add(TEST_STRING)

        assertEquals(
            TEST_STRING,
            noteRepository.getDataById(TEST_ID).asLiveData().getOrAwaitValue()
        )

        coVerify {
            noteRepository.getDataById(TEST_ID)
            noteRepository.addNote(TEST_STRING)
        }
    }

    @Test
    fun updateTest() {
        coEvery { noteRepository.getDataById(TEST_ID) } returns flow {
            emit(TEST_STRING)
        }
        coEvery { noteRepository.updateNote(TEST_ID, TEST_STRING) } answers {}
        detailPresenter.update(TEST_ID, TEST_STRING)

        assertEquals(
            TEST_STRING,
            noteRepository.getDataById(TEST_ID).asLiveData().getOrAwaitValue()
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
        detailPresenter.insertOrUpdate(TEST_ID, TEST_STRING)
        coVerify {
            noteRepository.updateNote(TEST_ID, TEST_STRING)
        }
        detailPresenter.insertOrUpdate(TEST_EMPTY_ID, TEST_STRING)
        coVerify {
            noteRepository.addNote(TEST_STRING)
        }
    }

    @Test
    fun checkingWhiteSpacesTest() {
        assertEquals(true, detailPresenter.checkingWhiteSpaces(TEST_EMPTY_STRING.length))
        assertEquals(false, detailPresenter.checkingWhiteSpaces(TEST_STRING.length))
    }
}
