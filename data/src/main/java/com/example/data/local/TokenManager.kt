package com.example.data.local

import android.content.SharedPreferences

class TokenManager(private val prefs: SharedPreferences) {
  companion object {
    private const val USER_TOKEN = "access_token"
    private const val REFRESH_TOKEN = "refresh_token"
  }

  var accessToken: String?
    get() = prefs.getString(USER_TOKEN, null)
    set(value) = prefs.edit().putString(USER_TOKEN, value).apply()

  var refreshToken: String?
    get() = prefs.getString(REFRESH_TOKEN, null)
    set(value) = prefs.edit().putString(REFRESH_TOKEN, value).apply()

  fun saveTokens(
    accessToken: String,
    refreshToken: String
  ) {
    this.accessToken = accessToken
    this.refreshToken = refreshToken
  }

  fun deleteTokens() {
    prefs.edit()
      .clear()
      .apply()
  }

  fun isUserLoggedIn(): Boolean {
    return accessToken != null && refreshToken != null
  }
}