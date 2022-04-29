package com.exampleapp.mvpapp_kotlin.presenter

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.asLiveData
import com.exampleapp.mvpapp_kotlin.entity.Note
import com.exampleapp.mvpapp_kotlin.nullString
import com.exampleapp.mvpapp_kotlin.repository.NoteRepository
import com.exampleapp.mvpapp_kotlin.testId
import com.exampleapp.mvpapp_kotlin.testString
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
class DetailPresenterTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    private lateinit var detailPresenter: DetailPresenter
    private val noteRepository: NoteRepository = mockk()

    @Test
    fun getNoteByIdTest() {
        coEvery { noteRepository.getDataById(testId) } returns flow {
            emit(testString)
        }
        detailPresenter = DetailPresenter(noteRepository)
        val result = detailPresenter.getNoteById(testId).getOrAwaitValue()
        assertNotNull(result)
        assertEquals(testString, result)
    }

    @Test
    fun addTest() {
        coEvery { noteRepository.getDataById(testId) } returns flow {
            emit(testString)
        }
        coEvery { noteRepository.addNote(testString) } answers {}
        detailPresenter = DetailPresenter(noteRepository)
        detailPresenter.add(testString)

        assertEquals(testString, noteRepository.getDataById(testId).asLiveData().getOrAwaitValue())

        coVerify {
            noteRepository.getDataById(testId)
            noteRepository.addNote(testString)
        }
    }
}
