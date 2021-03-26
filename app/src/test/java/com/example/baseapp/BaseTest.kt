package com.example.baseapp

import android.os.Build
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.baseapp.injection.TestAppComponent
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(application = TestApplication::class, sdk = [Build.VERSION_CODES.O_MR1])
abstract class BaseTest {

  private lateinit var application: TestApplication

  @Before
  open fun setup() {
    application = ApplicationProvider.getApplicationContext() as TestApplication
    injectIntoDagger(application.provideComponent())
  }

  @Test
  fun test() {
    assert(true)
  }

  abstract fun injectIntoDagger(testAppComponent: TestAppComponent)
}