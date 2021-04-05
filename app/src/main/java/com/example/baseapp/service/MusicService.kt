package com.example.baseapp.service

import android.app.PendingIntent
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat.MediaItem
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import androidx.core.net.toUri
import androidx.media.MediaBrowserServiceCompat
import com.example.baseapp.util.AppConstants.SERVICE_TAG
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import javax.inject.Inject

class MusicService @Inject constructor(
  private val dataSourceFactory: DefaultDataSourceFactory,
  private val exoPlayer: ExoPlayer
) : MediaBrowserServiceCompat() {

  private val serviceJob = Job()
  private val serviceScope = CoroutineScope(Dispatchers.Main + serviceJob)

  private lateinit var mediaSession: MediaSessionCompat
  private lateinit var mediaSessionConnector: MediaSessionConnector
  private var curPlayingSong: MediaMetadataCompat? = null

  override fun onCreate() {
    super.onCreate()
    val activityIntent = packageManager?.getLaunchIntentForPackage(packageName)?.let {
      PendingIntent.getActivity(this, 0, it, 0)
    }

    mediaSession = MediaSessionCompat(this, SERVICE_TAG).apply {
      setSessionActivity(activityIntent)
      isActive = true
    }

    sessionToken = mediaSession.sessionToken

    mediaSessionConnector = MediaSessionConnector(mediaSession)
    mediaSessionConnector.setPlayer(exoPlayer)
  }

  fun preparePlayer(
    songs: List<MediaMetadataCompat>,
    index: Int,
    playNow: Boolean
  ) {
    val curSongIndex = if (curPlayingSong == null) 0 else index
    exoPlayer.setMediaSource(asConcatenatingMediaSource(songs))
    exoPlayer.seekTo(curSongIndex, 0L)
    exoPlayer.playWhenReady = playNow
  }

  override fun onGetRoot(
    clientPackageName: String,
    clientUid: Int,
    rootHints: Bundle?
  ): BrowserRoot? {
    TODO("Not yet implemented")
  }

  override fun onLoadChildren(
    parentId: String,
    result: Result<MutableList<MediaItem>>
  ) {
    TODO("Not yet implemented")
  }

  override fun onDestroy() {
    super.onDestroy()
    serviceScope.cancel()
  }

  private fun asConcatenatingMediaSource(
    songs: List<MediaMetadataCompat>
  ): ConcatenatingMediaSource {
    val concatenatingMediaSource = ConcatenatingMediaSource()
    songs.map { song ->
      val mediaId = song.getString(MediaMetadataCompat.METADATA_KEY_MEDIA_ID)
      val mediaSource =
        ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(mediaId.toUri())
      concatenatingMediaSource.addMediaSource(mediaSource)
    }
    return concatenatingMediaSource
  }
}