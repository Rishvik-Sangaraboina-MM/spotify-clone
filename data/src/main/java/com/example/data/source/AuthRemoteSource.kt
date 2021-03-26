package com.example.data.source

import com.example.data.remote.api.AuthApi
import com.example.data.util.safeApiCall
import com.example.domain.entity.ApiResponse
import com.example.domain.entity.LoginRequest
import com.example.domain.entity.LoginResponse
import com.example.domain.entity.SignUpRequest
import com.example.domain.source.IAuthRemoteSource
import com.example.domain.util.SafeResult

class AuthRemoteSource(private val authApi: AuthApi) : IAuthRemoteSource {
  override suspend fun login(loginRequest: LoginRequest): SafeResult<ApiResponse<LoginResponse>> {
    return safeApiCall { authApi.login(loginRequest) }
  }

  override suspend fun signUp(signUpRequest: SignUpRequest): SafeResult<ApiResponse<LoginResponse>> {
    return safeApiCall { authApi.signUp(signUpRequest) }
  }
}