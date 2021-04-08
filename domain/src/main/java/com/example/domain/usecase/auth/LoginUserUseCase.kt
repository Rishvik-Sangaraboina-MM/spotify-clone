package com.example.domain.usecase.auth

import com.example.domain.entity.Auth
import com.example.domain.entity.LoginRequest
import com.example.domain.repository.IAuthRepo
import com.example.domain.usecase.BaseUseCase
import com.example.domain.util.SafeResult

class LoginUserUseCase(private val authRepo: IAuthRepo) : BaseUseCase<LoginRequest, SafeResult<Auth>> {
  override suspend fun perform(executableParam: LoginRequest): SafeResult<Auth> {
    return authRepo.login(executableParam)
  }
}