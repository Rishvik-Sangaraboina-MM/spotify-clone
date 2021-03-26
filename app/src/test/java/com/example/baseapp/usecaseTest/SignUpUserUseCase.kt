package com.example.baseapp.usecaseTest

import com.example.baseapp.BaseTest
import com.example.baseapp.injection.TestAppComponent
import com.example.baseapp.utils.MockFailureDispatcher
import com.example.baseapp.utils.enqueueResponse
import com.example.data.local.TokenManager
import com.example.domain.entity.SignUpRequest
import com.example.domain.usecase.auth.SignUpUserUseCase
import com.example.domain.util.SafeResult.Failure
import com.example.domain.util.SafeResult.Success
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import javax.inject.Inject

class SignUpUserUseCase : BaseTest() {

  @Inject
  lateinit var mockWebServer: MockWebServer

  @Inject
  lateinit var signUpUserUseCase: SignUpUserUseCase

  @Inject
  lateinit var tokenManager: TokenManager

  private val signUpRequest = SignUpRequest(
    "rrrishvik@gmail.com", "Rishvik", "Sangaraboina", "cmHunk@789", "email", null, null,
    "rishvik007"
  )

  override fun injectIntoDagger(testAppComponent: TestAppComponent) {
    testAppComponent.inject(this)
  }

  @Before
  fun before() {
    mockWebServer.enqueueResponse("responses/login_response.json")
  }

  @Test
  fun `server works - returns success`() = runBlocking {
    val result = signUpUserUseCase.perform(signUpRequest)
    assert(result is Success)
  }

  @Test
  fun `server works - returns success, tokens are saved`() = runBlocking {
    val result = signUpUserUseCase.perform(signUpRequest)

    assert((result as Success).data.refreshToken.isNotEmpty())
    assert(result.data.accessToken.isNotEmpty())
    assert(result.data.accessToken == tokenManager.accessToken)
    assert(result.data.refreshToken == tokenManager.refreshToken)

  }

  @Test
  fun `server fails - returns failure`() = runBlocking {
    mockWebServer.dispatcher = MockFailureDispatcher()
    val result = signUpUserUseCase.perform(signUpRequest)
    assert(result is Failure)
  }

  @After
  fun tearDown() {
    mockWebServer.shutdown()
  }
}