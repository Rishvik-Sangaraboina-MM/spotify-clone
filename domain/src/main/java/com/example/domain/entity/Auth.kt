package com.example.domain.entity

data class Auth(
  val accessToken: String,
  val refreshToken: String,
  val user: User
)
