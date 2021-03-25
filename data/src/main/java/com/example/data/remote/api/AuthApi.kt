package com.example.data.remote.api

import com.example.domain.entity.ApiResponse
import com.example.domain.entity.LoginRequest
import com.example.domain.entity.LoginResponse
import com.example.domain.entity.SignUpRequest
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
  @POST("v1/auth/login")
  suspend fun login(
    @Body loginRequest: LoginRequest
  ): ApiResponse<LoginResponse>

  @POST("v1/users/registration")
  suspend fun signUp(
    @Body signUpRequest: SignUpRequest
  ): ApiResponse<LoginResponse>
}