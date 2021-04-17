package com.example.baseapp.ui.auth.login

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.replaceText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import com.example.baseapp.R
import com.example.baseapp.R.id
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class LoginActivityTest {

  @get:Rule
  var mActivityTestRule = ActivityScenarioRule(LoginActivity::class.java)

  @Test
  fun successful_login_flow() {
    val textInputEditText = onView(withId(R.id.edit_email))

    textInputEditText.perform(replaceText("rrrishvik@gmail.com"), closeSoftKeyboard())

    val textInputEditText2 = onView(withId(R.id.edit_password))

    textInputEditText2.perform(replaceText("cmHunk@789"), closeSoftKeyboard())

    val materialButton = onView(withId(R.id.btn_login))
    materialButton.perform(click())
    runBlocking {
      delay(5000)
    }
    val viewGroup = onView(withId(R.id.motionLayout))

    viewGroup.check(matches(isDisplayed()))
  }

  @Test
  fun invalid_username_password() {
    val textInputEditText = onView(withId(R.id.edit_email))
    textInputEditText.perform(replaceText("1234"), closeSoftKeyboard())
    val textInputEditText2 = onView(withId(R.id.edit_password))
    textInputEditText2.perform(replaceText("1234"), closeSoftKeyboard())
    val materialButton = onView(withId(R.id.btn_login))
    materialButton.perform(click())
  }

  @Test
  fun registration_flow() {
    val materialButton = onView(withId(id.btn_register))
    materialButton.perform(click())
    val linearLayout = onView(withId(android.R.id.content))
    linearLayout.check(matches(isDisplayed()))
  }
}
