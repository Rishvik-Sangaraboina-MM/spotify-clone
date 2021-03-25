package com.example.domain.entity

data class ApiResponse<T>(
  val message: String,
  val data: T
) {
  override fun toString(): String {
    return "$message\n$data"
  }
}
