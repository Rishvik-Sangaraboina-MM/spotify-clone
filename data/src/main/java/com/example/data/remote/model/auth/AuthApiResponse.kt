package com.example.data.remote.model.auth

data class AuthApiResponse<T>(
  val message: String,
  val data: T
) {
  override fun toString(): String {
    return "$message\n$data"
  }
}
