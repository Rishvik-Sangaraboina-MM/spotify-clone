package com.example.baseapp.utils

import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer

fun MockWebServer.enqueueResponse(
  responsePath: String? = null,
  responseCode: Int = 200
) {
  val mockResponse = MockResponse()
    .setBody(FileUtil.loadText("$responsePath"))
    .setResponseCode(responseCode)
  enqueue(mockResponse)
}


