package com.example.baseapp.util

import android.support.v4.media.MediaMetadataCompat
import android.util.Log
import androidx.core.net.toUri
import com.example.domain.entity.SongResponse
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

object AppConstants {
  const val APP_PREFERENCE_NAME = "music_pref"
  const val AUTH_BASE_URL =
    "http://starterki-tnest-dev.eba-2kssyddj.us-east-1.elasticbeanstalk.com/"
  const val MUSIC_BASE_URL = "https://itunes.apple.com"
  const val PLAYLIST_CHILL = "Chill"
  const val PLAYLIST_PARTY = "Party"
  const val PLAYLIST_SLEEP = "Sleep"
  const val PLAYLIST_ENERGY = "Energy"
  const val PLAYLIST_WORKOUT = "Workout"
}

fun asConcatenatingMediaSource(
  dataSourceFactory: DefaultDataSourceFactory,
  songs: List<MediaMetadataCompat>
): ConcatenatingMediaSource {
  val concatenatingMediaSource = ConcatenatingMediaSource()
  songs.mapIndexed { index, song ->
    val mediaId = song.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID) ?: kotlin.run {
      Log.e("MediaIDNullTag: ", index.toString())
      ""
    }
    val mediaSource =
      ProgressiveMediaSource.Factory(dataSourceFactory)
        .createMediaSource(com.google.android.exoplayer2.MediaItem.fromUri(mediaId.toUri()))
    concatenatingMediaSource.addMediaSource(mediaSource)
  }
  return concatenatingMediaSource
}

fun mapToMediaMetaDataCompatList(list: List<SongResponse>): List<MediaMetadataCompat> {
  return list.map {
    MediaMetadataCompat.Builder()
      .putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST, it.artistName)
      .putString(MediaMetadataCompat.METADATA_KEY_TITLE, it.trackName)
      .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE, it.trackCensoredName)
      .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, it.previewUrl)
      .putString(MediaMetadataCompat.METADATA_KEY_ART_URI, it.artworkUrl100)
      .build()
  }
}