package com.example.baseapp.util

import android.support.v4.media.MediaMetadataCompat
import android.util.Log
import androidx.core.net.toUri
import com.example.domain.entity.Song
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory

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