package com.example.domain.source

import com.example.domain.entity.Auth
import com.example.domain.entity.LoginRequest
import com.example.domain.entity.SignUpRequest
import com.example.domain.util.SafeResult

interface IAuthRemoteSource {
  suspend fun login(
    loginRequest: LoginRequest
  ): SafeResult<Auth>

  suspend fun signUp(
    signUpRequest: SignUpRequest
  ): SafeResult<Auth>
}