package com.example.data.util

import android.util.Log
import com.example.domain.util.SafeResult
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

internal suspend fun <T> safeApiCall(
  dispatcher: CoroutineDispatcher = Dispatchers.IO,
  apiCall: suspend () -> T
): SafeResult<T> {
  return withContext(dispatcher) {
    try {
      SafeResult.Success(apiCall.invoke())
    } catch (throwable: Throwable) {
      Log.e("safeApiCall", throwable.message.toString())
      when (throwable) {
        is IOException -> SafeResult.NetworkError()
        is HttpException -> SafeResult.Failure(throwable)
        else -> SafeResult.Failure(Exception(throwable))
      }
    }
  }
}
