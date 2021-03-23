package com.example.data.local

import android.content.SharedPreferences

class TokenManager(private val prefs: SharedPreferences) {
  companion object {
    private const val USER_TOKEN = "access_token"
    private const val REFRESH_TOKEN = "refresh_token"
  }

  private fun saveAccessToken(token: String) {
    val editor = prefs.edit()
    editor.putString(USER_TOKEN, token)
    editor.apply()
  }

  fun fetchAccessToken(): String? {
    return prefs.getString(USER_TOKEN, null)
  }

  private fun saveRefreshToken(token: String) {
    val editor = prefs.edit()
    editor.putString(REFRESH_TOKEN, token)
    editor.apply()
  }

  fun fetchRefreshToken(): String? {
    return prefs.getString(REFRESH_TOKEN, null)
  }

  fun saveTokens(
    accessToken: String,
    refreshToken: String
  ) {
    saveAccessToken(accessToken)
    saveRefreshToken(refreshToken)
  }

  fun deleteTokens() {
    prefs.edit()
      .clear()
      .apply()
  }

  fun isUserLoggedIn(): Boolean {
    return fetchAccessToken() != null && fetchRefreshToken() != null
  }
}