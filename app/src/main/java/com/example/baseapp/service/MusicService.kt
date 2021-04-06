package com.example.baseapp.service

import android.app.PendingIntent
import android.os.Bundle
import android.support.v4.media.MediaBrowserCompat.MediaItem
import android.support.v4.media.MediaMetadataCompat
import android.support.v4.media.session.MediaSessionCompat
import android.util.Log
import androidx.core.net.toUri
import androidx.media.MediaBrowserServiceCompat
import com.example.baseapp.service.callback.MusicPlayerEventListener
import com.example.baseapp.service.callback.MusicPlayerNotificationListener
import com.example.baseapp.util.AppConstants.MEDIA_ROOT_ID
import com.example.baseapp.util.AppConstants.SERVICE_TAG
import com.example.baseapp.util.BaseApplication
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.ext.mediasession.MediaSessionConnector
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import javax.inject.Inject

class MusicService : MediaBrowserServiceCompat() {

  @Inject
  lateinit var dataSourceFactory: DefaultDataSourceFactory

  @Inject
  lateinit var exoPlayer: ExoPlayer

  private lateinit var musicNotificationManager: MusicNotificationManager

  private lateinit var mediaSession: MediaSessionCompat
  private lateinit var mediaSessionConnector: MediaSessionConnector
  private val musicPlayerEventListener: MusicPlayerEventListener
    get() = MusicPlayerEventListener(this)

  var isForegroundService = false

  override fun onCreate() {
    (application as BaseApplication).provideComponent().inject(this)
    super.onCreate()
    val activityIntent = packageManager?.getLaunchIntentForPackage(packageName)?.let {
      PendingIntent.getActivity(this, 0, it, 0)
    }
    mediaSession = MediaSessionCompat(this, SERVICE_TAG).apply {
      setSessionActivity(activityIntent)
      isActive = true
    }
    sessionToken = mediaSession.sessionToken
    musicNotificationManager = MusicNotificationManager(
      this, mediaSession.sessionToken, MusicPlayerNotificationListener(this)
    ) {

    }
    mediaSessionConnector = MediaSessionConnector(mediaSession)
    mediaSessionConnector.setPlayer(exoPlayer)
    exoPlayer.addListener(musicPlayerEventListener)
    musicNotificationManager.showNotification(exoPlayer)
  }

  private fun preparePlayer(
    songs: List<MediaMetadataCompat>,
    index: Int,
    playNow: Boolean
  ) {
    exoPlayer.setMediaSource(asConcatenatingMediaSource(songs))
    exoPlayer.seekTo(index, 0L)
    exoPlayer.prepare()
    exoPlayer.playWhenReady = playNow
  }

  override fun onGetRoot(
    clientPackageName: String,
    clientUid: Int,
    rootHints: Bundle?
  ): BrowserRoot {
    return BrowserRoot(MEDIA_ROOT_ID, null)
  }

  override fun onLoadChildren(
    parentId: String,
    result: Result<MutableList<MediaItem>>
  ) {
    result.sendResult(null)
  }

  override fun onDestroy() {
    super.onDestroy()
    exoPlayer.release()
    exoPlayer.removeListener(musicPlayerEventListener)
  }

  private fun asConcatenatingMediaSource(
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
}