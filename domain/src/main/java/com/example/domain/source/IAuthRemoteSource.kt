package com.example.domain.source

import com.example.domain.entity.ApiResponse
import com.example.domain.entity.LoginRequest
import com.example.domain.entity.LoginResponse
import com.example.domain.util.SafeResult

interface IAuthRemoteSource {
  suspend fun login(
    loginRequest: LoginRequest
  ): SafeResult<ApiResponse<LoginResponse>>
}