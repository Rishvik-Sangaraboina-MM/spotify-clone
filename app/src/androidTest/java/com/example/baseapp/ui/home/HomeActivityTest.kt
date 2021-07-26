package com.example.baseapp.ui.home

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.baseapp.R
import com.example.baseapp.ui.home.music.SongsRecyclerAdapter.SongViewHolder
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class HomeActivityTest {

  @get:Rule
  val testRule = ActivityScenarioRule(HomeActivity::class.java)

  @Test
  fun display_recycler_view() {
    val recyclerView = onView(withId(R.id.music_recyclerView))
    recyclerView.check(matches(isDisplayed()))
  }

  @Test
  fun select_item() {
    val recyclerView = onView(withId(R.id.music_recyclerView))
    runBlocking {
      delay(3000)
    }
    recyclerView.perform(actionOnItemAtPosition<SongViewHolder>(2, click()))
    onView(withId(R.id.seekBar)).check(matches(isDisplayed()))
  }
}