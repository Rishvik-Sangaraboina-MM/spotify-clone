package com.example.domain.entity

data class SignUpRequest(
  val email: String,
  val firstName: String,
  val lastName: String,
  val password: String?,
  val registrationType: String,
  val socialIdentityId: String?,
  val socialIdentityToken: String?,
  val userName: String?
)
