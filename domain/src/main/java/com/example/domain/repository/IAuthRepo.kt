package com.example.domain.repository

import com.example.domain.entity.Auth
import com.example.domain.entity.LoginRequest
import com.example.domain.entity.SignUpRequest
import com.example.domain.util.SafeResult

interface IAuthRepo {

  suspend fun login(
    loginRequest: LoginRequest
  ): SafeResult<Auth>

  suspend fun signUp(signUpRequest: SignUpRequest): SafeResult<Auth>

  suspend fun logout(): SafeResult<Unit>

  suspend fun isUserLoggedIn(): Boolean
}