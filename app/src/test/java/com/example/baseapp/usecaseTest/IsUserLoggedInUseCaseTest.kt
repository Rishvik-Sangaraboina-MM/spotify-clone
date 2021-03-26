package com.example.baseapp.usecaseTest

import com.example.baseapp.BaseTest
import com.example.baseapp.injection.TestAppComponent
import com.example.baseapp.utils.enqueueResponse
import com.example.data.local.TokenManager
import com.example.domain.entity.LoginRequest
import com.example.domain.usecase.auth.IsUserLoggedInUseCase
import com.example.domain.usecase.auth.LoginUserUseCase
import com.example.domain.usecase.auth.LogoutUserUseCase
import com.example.domain.util.SafeResult.Success
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class IsUserLoggedInUseCaseTest : BaseTest() {

  @Inject
  lateinit var isUserLoggedInUseCase: IsUserLoggedInUseCase

  @Inject
  lateinit var loginUserUseCase: LoginUserUseCase

  @Inject
  lateinit var tokenManager: TokenManager

  @Inject
  lateinit var logoutUserUseCase: LogoutUserUseCase

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
  fun `server works, login, isUserLoggedIn - returns true`() = runBlocking {
    val result = loginUserUseCase.perform(loginRequest)
    assert(result is Success)
    assert(isUserLoggedInUseCase.perform())
    assert(tokenManager.accessToken != null)
    assert(tokenManager.refreshToken != null)
  }

  @Test
  fun `server works, login, logout, isUserLoggedIn - returns false`() = runBlocking {
    val result = loginUserUseCase.perform(loginRequest)
    assert(result is Success)
    logoutUserUseCase.perform()
    assert(!isUserLoggedInUseCase.perform())
    assert(tokenManager.accessToken == null)
    assert(tokenManager.refreshToken == null)
  }

  @After
  fun tearDown() {
    mockWebServer.shutdown()
  }
}