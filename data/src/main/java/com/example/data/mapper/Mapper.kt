package com.example.data.mapper

import com.example.data.remote.model.auth.AuthResponse
import com.example.data.remote.model.auth.UserResponse
import com.example.data.remote.model.music.MusicResponse
import com.example.data.remote.model.music.SongResponse
import com.example.domain.entity.Auth
import com.example.domain.entity.Music
import com.example.domain.entity.Song
import com.example.domain.entity.User

fun AuthResponse.toAuth(): Auth {
  return Auth(accessToken, refreshToken, user.toUser())
}

fun UserResponse.toUser(): User {
  return User(email, firstName, lastName, profileImage, uid, userName)
}

fun MusicResponse.toMusic(): Music {
  val list = results.map {
    it.toSong()
  }
  return Music(resultCount, list)
}

fun SongResponse.toSong(): Song {
  return Song(artistName, artworkUrl100, previewUrl, trackName)
}