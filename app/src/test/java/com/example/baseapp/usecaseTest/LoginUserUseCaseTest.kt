package com.example.baseapp.usecaseTest

import android.os.Build
import com.example.baseapp.BaseTest
import com.example.baseapp.injection.TestAppComponent
import com.example.baseapp.utils.MockFailureDispatcher
import com.example.baseapp.utils.enqueueResponse
import com.example.data.local.TokenManager
import com.example.domain.entity.LoginRequest
import com.example.domain.usecase.auth.LoginUserUseCase
import com.example.domain.util.SafeResult.Failure
import com.example.domain.util.SafeResult.Success
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.robolectric.annotation.Config
import javax.inject.Inject

@Config(sdk = [Build.VERSION_CODES.P])
class LoginUserUseCaseTest : BaseTest() {

  @Inject
  lateinit var loginUserUseCase: LoginUserUseCase

  @Inject
  lateinit var tokenManager: TokenManager

  private val loginRequest = LoginRequest("rrrishvik@gmail.com", "email", "cmHunk@789", null)

  override fun injectIntoDagger(testAppComponent: TestAppComponent) {
    testAppComponent.inject(this)
  }

  override fun setup() {
    super.setup()
    mockWebServer.enqueueResponse("responses/login_response.json")
  }

  @Test
  fun `server works - returns success`() = runBlocking {
    val result = loginUserUseCase.perform(loginRequest)
    assert(result is Success)
  }

  @Test
  fun `server works - returns success, tokens are saved`() = runBlocking {
    val result = loginUserUseCase.perform(loginRequest)

    assert((result as Success).data.refreshToken.isNotEmpty())
    assert(result.data.accessToken.isNotEmpty())
    assert(result.data.accessToken == tokenManager.accessToken)
    assert(result.data.refreshToken == tokenManager.refreshToken)
  }

  @Test
  fun `server fails - returns failure`() = runBlocking {
    mockWebServer.dispatcher = MockFailureDispatcher()
    val result = loginUserUseCase.perform(loginRequest)
    assert(result is Failure)
  }
}