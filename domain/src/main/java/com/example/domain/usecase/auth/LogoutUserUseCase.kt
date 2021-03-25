package com.example.domain.usecase.auth

import com.example.domain.repository.IAuthRepo
import com.example.domain.usecase.BaseUseCase
import com.example.domain.util.SafeResult

class LogoutUserUseCase(private val authRepo : IAuthRepo) : BaseUseCase<Nothing, SafeResult<Unit>> {
  override suspend fun perform(): SafeResult<Unit> {
    return authRepo.logout()
  }
}