package com.example.domain.usecase.auth

import com.example.domain.repository.IAuthRepo
import com.example.domain.usecase.BaseUseCase

class IsUserLoggedInUseCase(private val authRepo: IAuthRepo) : BaseUseCase<Unit, Boolean> {

  override suspend fun perform(): Boolean {
    return authRepo.isUserLoggedIn()
  }
}