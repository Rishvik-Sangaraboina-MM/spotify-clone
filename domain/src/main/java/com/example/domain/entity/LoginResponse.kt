package com.example.domain.entity

data class LoginResponse(
  val accessToken: String,
  val refreshToken: String,
  val user: UserResponse
)