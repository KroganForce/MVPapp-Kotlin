package com.example_app.mvpapp_kotlin.ui

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example_app.mvpapp_kotlin.R
import com.example_app.mvpapp_kotlin.adapter.NoteHolder
import com.example_app.mvpapp_kotlin.utilities.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@MediumTest
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class HomeFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var navController: TestNavHostController

    @Before
    fun setUp() {
        hiltRule.inject()
        navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )
        navController.setGraph(R.navigation.nav_graph)

        launchFragmentInHiltContainer<HomeFragment>(
            null, R.style.Theme_AppCompat, navController
        )
    }

    @Test
    fun clickOnAddNoteFloatActionButtonTest() {
        onView(withId(R.id.float_action_button)).perform(click()).check(matches(isDisplayed()))
    }

    @Test
    fun clickOnItemNoteFromRecyclerViewTest() {
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))

        val itemElementText =
            ApplicationProvider.getApplicationContext<Context>().resources.getString(
                R.string.test_string
            )
        onView(withText(itemElementText)).check(matches(isDisplayed()))

        onView(withId(R.id.recycler_view)).perform(
            RecyclerViewActions.actionOnItemAtPosition<NoteHolder>(0, click())
        )
    }
}
