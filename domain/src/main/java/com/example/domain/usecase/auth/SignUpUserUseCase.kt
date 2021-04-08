package com.example.domain.usecase.auth

import com.example.domain.entity.Auth
import com.example.domain.entity.SignUpRequest
import com.example.domain.repository.IAuthRepo
import com.example.domain.usecase.BaseUseCase
import com.example.domain.util.SafeResult

class SignUpUserUseCase(private val authRepo: IAuthRepo) : BaseUseCase<SignUpRequest, SafeResult<Auth>> {
  override suspend fun perform(executableParam: SignUpRequest): SafeResult<Auth> {
    return authRepo.signUp(executableParam)
  }
}