package com.example.domain.source

interface IAuthLocalSource {
  suspend fun saveTokens(
    accessToken: String,
    refreshToken: String
  )
  suspend fun deleteTokens()

  suspend fun isUserLoggedIn() : Boolean

}