package com.example.baseapp.usecaseTest

import com.example.baseapp.BaseTest
import com.example.baseapp.injection.TestAppComponent
import com.example.baseapp.utils.MockFailureDispatcher
import com.example.baseapp.utils.enqueueResponse
import com.example.domain.usecase.music.FetchMusicUseCase
import com.example.domain.util.SafeResult.Failure
import com.example.domain.util.SafeResult.Success
import kotlinx.coroutines.runBlocking
import org.junit.Test
import javax.inject.Inject

class FetchMusicUseCaseTest : BaseTest() {

  @Inject
  lateinit var fetchMusicUseCase: FetchMusicUseCase

  override fun injectIntoDagger(testAppComponent: TestAppComponent) {
    testAppComponent.inject(this)
  }

  override fun setup() {
    super.setup()
    mockWebServer.enqueueResponse("responses/music_response.json")
  }

  @Test
  fun `server works - return success`() = runBlocking {
    val result = fetchMusicUseCase.perform("music")
    assert(result is Success)
  }

  @Test
  fun `server fails - return failure`() = runBlocking {
    mockWebServer.dispatcher = MockFailureDispatcher()
    val result = fetchMusicUseCase.perform("music")
    assert(result is Failure)
  }
}