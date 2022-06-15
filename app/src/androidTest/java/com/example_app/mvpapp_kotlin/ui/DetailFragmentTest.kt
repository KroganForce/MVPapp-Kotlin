package com.example_app.mvpapp_kotlin.ui

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.clearText
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.example_app.mvpapp_kotlin.R
import com.example_app.mvpapp_kotlin.TEST_NAVIGATION_ID
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
class DetailFragmentTest {

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

        val fragmentArgs = DetailFragmentArgs(TEST_NAVIGATION_ID).toBundle()
        launchFragmentInHiltContainer<DetailFragment>(
            fragmentArgs, R.style.Theme_AppCompat, navController
        )
    }

    @Test
    fun editTextTest() {
        onView(withId(R.id.edit_text_view))
            .check(matches(isDisplayed()))
        onView(withId(R.id.edit_text_view)).perform(clearText())
        onView(withId(R.id.edit_text_view)).perform(ViewActions.typeText("TEST_NOTE_1"))
    }

    @Test
    fun floatAddButtonTest() {
        onView(withId(R.id.float_add_button)).perform(click()).check(
            matches(
                isDisplayed()
            )
        )
    }

    @Test
    fun emptyNoteAndSnackBarTextTest() {
        onView(withId(R.id.edit_text_view)).perform(clearText()).check(
            matches(
                isDisplayed()
            )
        )
        onView(withId(R.id.float_add_button)).perform(click()).check(matches(isDisplayed()))

        val snackBarText =
            ApplicationProvider.getApplicationContext<Context>().resources.getString(
                R.string.empty_note
            )
        onView(ViewMatchers.withText(snackBarText)).check(matches(isDisplayed()))
    }
}
