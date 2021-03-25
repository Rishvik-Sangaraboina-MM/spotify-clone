package com.example.domain.entity

data class LoginRequest(
  val email: String,
  val loginType: String,
  val password: String,
  val token: String?
)