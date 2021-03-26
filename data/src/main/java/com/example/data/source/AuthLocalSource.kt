package com.example.data.source

import com.example.data.local.TokenManager
import com.example.domain.source.IAuthLocalSource

class AuthLocalSource(private val tokenManager: TokenManager) : IAuthLocalSource {
  override suspend fun saveTokens(
    accessToken: String,
    refreshToken: String
  ) {
    tokenManager.saveTokens(accessToken, refreshToken)
  }

  override suspend fun deleteTokens() {
    tokenManager.deleteTokens()
  }

  override suspend fun isUserLoggedIn(): Boolean = tokenManager.isUserLoggedIn()
}