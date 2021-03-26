package com.example.baseapp.usecaseTest

import com.example.baseapp.BaseTest
import com.example.baseapp.injection.TestAppComponent
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import javax.inject.Inject

class SignInUserUseCase : BaseTest() {

  @Inject
  lateinit var mockWebServer: MockWebServer

  override fun injectIntoDagger(testAppComponent: TestAppComponent) {
    testAppComponent.inject(this)
  }

  @After
  fun tearDown() {
    mockWebServer.shutdown()
  }
}