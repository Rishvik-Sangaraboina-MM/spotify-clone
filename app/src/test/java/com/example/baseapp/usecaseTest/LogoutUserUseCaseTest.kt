package com.example.baseapp.usecaseTest

import com.example.baseapp.BaseTest
import com.example.baseapp.injection.TestAppComponent
import com.example.baseapp.utils.enqueueResponse
import com.example.data.local.TokenManager
import com.example.domain.entity.LoginRequest
import com.example.domain.usecase.auth.LoginUserUseCase
import com.example.domain.usecase.auth.LogoutUserUseCase
import com.example.domain.util.SafeResult.Success
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class LogoutUserUseCaseTest : BaseTest() {

  @Inject
  lateinit var logoutUserUseCase: LogoutUserUseCase

  @Inject
  lateinit var loginUserUseCase: LoginUserUseCase

  @Inject
  lateinit var tokenManager: TokenManager

  private val loginRequest = LoginRequest("rrrishvik@gmail.com", "email", "cmHunk@789", null)

  @Inject
  lateinit var mockWebServer: MockWebServer

  override fun injectIntoDagger(testAppComponent: TestAppComponent) {
    testAppComponent.inject(this)
  }

  @Before
  fun before() {
    mockWebServer.enqueueResponse("responses/login_response.json")
  }

  @Test
  fun `server works, login, logout - returns success`() = runBlocking {
    val result = loginUserUseCase.perform(loginRequest)
    assert(result is Success)
    val logOutResult = logoutUserUseCase.perform()
    assert(logOutResult is Success)
    assert(tokenManager.refreshToken == null)
    assert(tokenManager.accessToken == null)
  }

  @After
  fun tearDown() {
    mockWebServer.shutdown()
  }
}