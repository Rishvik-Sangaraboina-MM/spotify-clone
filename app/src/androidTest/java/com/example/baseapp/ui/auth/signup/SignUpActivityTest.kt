package com.example.baseapp.ui.auth.signup

import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Rule
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4ClassRunner::class)
class SignUpActivityTest {

  @get:Rule
  val testRule = ActivityScenarioRule(SignUpActivity::class.java)
}