package com.example.data.mapper

import com.example.data.local.model.SongEntity
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

fun SongResponse.toSongEntity(keyword: String): SongEntity {
  return SongEntity(
    keyword, artistId, artistName, artistViewUrl, artworkUrl100, artworkUrl30, artworkUrl60,
    collectionArtistName, collectionCensoredName, collectionExplicitness, collectionId,
    collectionName, collectionPrice, collectionViewUrl, country, currency, discCount, discNumber,
    isStreamable, kind, previewUrl, primaryGenreName, releaseDate, trackCensoredName, trackCount,
    trackExplicitness, trackId, trackName, trackNumber, trackPrice, trackTimeMillis, trackViewUrl,
    wrapperType
  )
}

fun SongEntity.toSong(): Song {
  return Song(artistName, artworkUrl100, previewUrl, trackName)
}