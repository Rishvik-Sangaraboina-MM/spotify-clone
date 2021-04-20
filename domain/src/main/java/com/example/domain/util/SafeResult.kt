package com.example.domain.util

sealed class SafeResult<out T> {
  data class Success<T>(val data: T) : SafeResult<T>()
  data class Failure(
    val exception: Exception? = Exception("Unknown Error"),
    val message: String = exception?.localizedMessage ?: ""
  ) : SafeResult<Nothing>()

  data class NetworkError<T>(val cache: T? = null) : SafeResult<T>()

  override fun toString(): String {
    return when (this) {
      is Success -> "Success[data=$data]"
      is Failure -> "Failure[exception=$exception]"
      is NetworkError -> "NetworkError"
    }
  }
}
