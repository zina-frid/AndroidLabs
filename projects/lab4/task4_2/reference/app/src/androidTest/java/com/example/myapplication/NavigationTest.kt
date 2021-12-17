package com.example.myapplication

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Test
import org.junit.runner.RunWith
import android.content.pm.ActivityInfo
import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import org.junit.Rule


/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class NavigationTest {
    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun testFragmentsExist() {
        // first fragment
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(doesNotExist())
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(doesNotExist())

        onView(withId(R.id.bnToSecond)).perform(click())

        // second fragment
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(doesNotExist())
        onView(withId(R.id.bnToThird)).check(matches(isDisplayed()))

        onView(withId(R.id.bnToThird)).perform(click())

        // third fragment
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(doesNotExist())

        // about activity
        openAbout()
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))

    }

    @Test
    fun testNavigation() {
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))

        openAbout()

        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.bnToSecond)).perform(click())

        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(matches(isDisplayed()))

        openAbout()

        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.bnToThird)).perform(click())

        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))

        openAbout()

        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))
    }

    @Test
    fun testAbout() {
        launchActivity<MainActivity>()
        openAbout()
        Espresso.onView(ViewMatchers.withId(R.id.activity_about))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testBackStack() {
        // fill backstack
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.bnToFirst)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToFirst)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())

        openAbout()

        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(matches(isDisplayed()))

        pressBack()

        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))

        try {
            pressBack()
            assert(false)
        } catch (NoActivityResumedException: Exception) {
            assert(true)
        }
    }

    @Test
    fun testRotationScreen() {

        // first fragment
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        Thread.sleep(1000)

        onView(withId(R.id.bnToSecond)).perform(click())

        // second fragment
        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(matches(isDisplayed()))

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)

        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(matches(isDisplayed()))

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        Thread.sleep(1000)

        onView(withId(R.id.bnToThird)).perform(click())

        // third fragment
        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)

        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        Thread.sleep(1000)

        openAbout()

        // about activity
        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
        Thread.sleep(1000)

        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))

        activityRule.scenario.onActivity { activity ->
            activity.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        }
        Thread.sleep(1000)
    }

    @Test
    fun testUpNavigation() {
        //first
        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(doesNotExist())
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(doesNotExist())

        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())

        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(doesNotExist())
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(doesNotExist())

        //second
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())

        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(doesNotExist())
        onView(withId(R.id.bnToThird)).check(matches(isDisplayed()))

        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())

        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(doesNotExist())
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(doesNotExist())

        //third
        onView(withId(R.id.bnToSecond)).perform(click())
        onView(withId(R.id.bnToThird)).perform(click())
        onView(withId(R.id.bnToSecond)).perform(click())

        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())

        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(doesNotExist())
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(doesNotExist())

        openAbout()

        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))

        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())

        onView(withId(R.id.fragment1)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(doesNotExist())
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(doesNotExist())

        onView(withId(R.id.bnToSecond)).perform(click())
        openAbout()

        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))

        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())

        onView(withId(R.id.fragment2)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(doesNotExist())
        onView(withId(R.id.bnToThird)).check(matches(isDisplayed()))

        onView(withId(R.id.bnToThird)).perform(click())
        openAbout()

        onView(withId(R.id.activity_about)).check(matches(isDisplayed()))
        onView(withId(R.id.tvAbout)).check(matches(isDisplayed()))

        onView(withContentDescription(R.string.nav_app_bar_navigate_up_description)).perform(click())

        onView(withId(R.id.fragment3)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToFirst)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToSecond)).check(matches(isDisplayed()))
        onView(withId(R.id.bnToThird)).check(doesNotExist())
    }
}