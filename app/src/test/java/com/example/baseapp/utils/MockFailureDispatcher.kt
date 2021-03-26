package com.example.baseapp.utils

import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.RecordedRequest

class MockFailureDispatcher : Dispatcher() {
  override fun dispatch(request: RecordedRequest): MockResponse {
    return MockResponse().setResponseCode(501)
  }
}