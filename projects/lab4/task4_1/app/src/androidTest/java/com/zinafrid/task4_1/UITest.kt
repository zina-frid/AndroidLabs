package com.zinafrid.task4_1

import android.app.Activity
import android.content.pm.ActivityInfo
import android.os.SystemClock.sleep
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class UITest {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun buttonTest() {
        onView(withId(R.id.button))
            .check(matches(withText(R.string.click)))
            .perform(click())
            .check(matches(withText(R.string.clicked)))
            .perform(click())
            .check(matches(withText(R.string.clicked)))

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }

        onView(withId(R.id.button))
            .check(matches(withText(R.string.click)))
            .perform(click())
            .check(matches(withText(R.string.clicked)))
            .perform(click())
            .check(matches(withText(R.string.clicked)))
    }

    @Test
    fun editTextCheck() {
        onView(withId(R.id.editText))
            .check(matches(withText("")))
            .perform(typeText("Test"))

        activityRule.scenario.recreate()
//        activityRule.scenario.onActivity { activity ->
//            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
//        }
        onView(withId(R.id.editText))
            .check(matches(withText("Test")))
    }
}