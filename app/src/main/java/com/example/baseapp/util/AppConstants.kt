package com.example.baseapp.util

import android.support.v4.media.MediaMetadataCompat
import android.util.Log
import androidx.core.net.toUri
import com.example.domain.entity.Song
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.MediaSource
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
  const val CHANNEL_ID = "musicPlayerServiceChannel"
  const val SERVICE_TAG = "MusicService"
}

fun asMediaSource(
  dataSourceFactory: DefaultDataSourceFactory,
  metadataCompat: MediaMetadataCompat
): MediaSource {
  val mediaId = metadataCompat.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID) ?: kotlin.run {
    Log.e("MediaIDNullTag: ", metadataCompat.toString())
    ""
  }
  return ProgressiveMediaSource.Factory(dataSourceFactory)
    .createMediaSource(com.google.android.exoplayer2.MediaItem.fromUri(mediaId.toUri()))
}

fun asMediaMetadataCompat(songResponse: Song): MediaMetadataCompat {
  return MediaMetadataCompat.Builder()
    .putString(MediaMetadataCompat.METADATA_KEY_ALBUM_ARTIST, songResponse.artistName)
    .putString(MediaMetadataCompat.METADATA_KEY_TITLE, songResponse.trackName)
    .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_TITLE, songResponse.trackName)
    .putString(MediaMetadataCompat.METADATA_KEY_DISPLAY_SUBTITLE, songResponse.artistName)
    .putString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID, songResponse.previewUrl)
    .putString(MediaMetadataCompat.METADATA_KEY_ART_URI, songResponse.artworkUrl100)
    .build()
}

fun asConcatenatingMediaSource(
  dataSourceFactory: DefaultDataSourceFactory,
  list: List<Song>
): ConcatenatingMediaSource {
  val concatenatingMediaSource = ConcatenatingMediaSource()
  list.map {
    val mediaMetadataCompat = asMediaMetadataCompat(it)
    concatenatingMediaSource.addMediaSource(asMediaSource(dataSourceFactory, mediaMetadataCompat))
  }
  return concatenatingMediaSource
}