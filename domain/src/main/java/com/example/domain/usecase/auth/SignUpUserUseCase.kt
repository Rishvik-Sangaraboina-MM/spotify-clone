package com.example.domain.usecase.auth

import com.example.domain.entity.LoginResponse
import com.example.domain.entity.SignUpRequest
import com.example.domain.repository.IAuthRepo
import com.example.domain.usecase.BaseUseCase
import com.example.domain.util.SafeResult

class SignUpUserUseCase(private val authRepo: IAuthRepo) : BaseUseCase<SignUpRequest, SafeResult<LoginResponse>> {
  override suspend fun perform(executableParam: SignUpRequest): SafeResult<LoginResponse> {
    return authRepo.signUp(executableParam)
  }
}