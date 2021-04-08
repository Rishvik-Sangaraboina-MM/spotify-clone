package com.example.data.remote.model.auth

data class AuthResponse(
  val accessToken: String,
  val refreshToken: String,
  val user: UserResponse
)