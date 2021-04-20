package com.example.baseapp.musicService

import android.app.PendingIntent
import android.content.Intent
import android.os.Binder
import android.os.Bundle
import android.os.IBinder
import android.support.v4.media.MediaBrowserCompat.MediaItem
import android.support.v4.media.session.MediaControllerCompat
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.MediaSessionCompat.Token
import androidx.media.MediaBrowserServiceCompat
import com.example.baseapp.R
import com.example.baseapp.ui.home.HomeActivity
import com.example.baseapp.util.AppConstants
import com.example.baseapp.util.AppConstants.SERVICE_TAG
import com.example.baseapp.util.BaseApplication
import com.example.baseapp.util.OnSongChangeListener
import com.example.baseapp.util.asConcatenatingMediaSource
import com.example.baseapp.util.asMediaMetadataCompat
import com.example.domain.entity.Song
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Player.EventListener
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.ui.PlayerNotificationManager
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import javax.inject.Inject

class MusicService : MediaBrowserServiceCompat(), EventListener {

  private val ONGOING_NOTIFICATION_ID = 100

  @Inject
  lateinit var dataSourceFactory: DefaultDataSourceFactory

  @Inject
  lateinit var exoPlayer: ExoPlayer

  private var onSongChangeListener: OnSongChangeListener? = null

  private var currentPlayList: List<Song>? = null

  private var currentSongIndex: Int = -1

  private val mBinder: IBinder = MusicServiceBinder()

  private lateinit var mediaSession: MediaSessionCompat

  private lateinit var mediaController: MediaControllerCompat

  override fun onStartCommand(
    intent: Intent?,
    flags: Int,
    startId: Int
  ): Int {
    (application as BaseApplication).component.inject(this)
    val pendingIntent: PendingIntent =
      Intent(this, HomeActivity::class.java).let { notificationIntent ->
        PendingIntent.getActivity(this, 0, notificationIntent, 0)
      }
    mediaSession = MediaSessionCompat(this, SERVICE_TAG).apply {
      setSessionActivity(pendingIntent)
      isActive = true
    }
    sessionToken = mediaSession.sessionToken
    exoPlayer.addListener(this)
    mediaController = MediaControllerCompat(applicationContext, mediaSession.sessionToken)
    createNotification(mediaSession.sessionToken)
    return START_NOT_STICKY
  }

  private fun createNotification(sessionToken: Token) {
    PlayerNotificationManager.createWithNotificationChannel(
      this, AppConstants.CHANNEL_ID, R.string.channel_name,
      R.string.channel_description, ONGOING_NOTIFICATION_ID,
      DescriptionAdapter(applicationContext, mediaController)
    ).apply {
      setSmallIcon(R.drawable.exo_ic_play_circle_filled)
      setMediaSessionToken(sessionToken)
      setPlayer(exoPlayer)
    }
  }

  override fun onBind(intent: Intent?): IBinder {
    return mBinder
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

  fun preparePlaylist(
    songs: List<Song>,
    index: Int,
    playNow: Boolean
  ) {
    if (currentPlayList != songs) {
      exoPlayer.setMediaSource(asConcatenatingMediaSource(dataSourceFactory, songs))
      currentPlayList = songs
      currentSongIndex = -1
    }
    if (currentSongIndex != index) {
      currentSongIndex = index
      exoPlayer.seekTo(currentSongIndex, 0L)
      exoPlayer.prepare()
      mediaSession.setMetadata(currentPlayList?.let { asMediaMetadataCompat(it[currentSongIndex]) })
      exoPlayer.playWhenReady = playNow
    }
  }

  override fun onTracksChanged(
    trackGroups: TrackGroupArray,
    trackSelections: TrackSelectionArray
  ) {
    super.onTracksChanged(trackGroups, trackSelections)
    currentSongIndex = exoPlayer.currentWindowIndex
    mediaSession.setMetadata(currentPlayList?.let { asMediaMetadataCompat(it[currentSongIndex]) })
    onSongChangeListener?.onSongChange(currentPlayList?.get(currentSongIndex))
  }

  fun skipNext() {
    exoPlayer.next()
    currentSongIndex = exoPlayer.currentWindowIndex
  }

  fun skipPrevious() {
    exoPlayer.previous()
    currentSongIndex = exoPlayer.currentWindowIndex
  }

  fun forward() {
    exoPlayer.seekTo(2000)
  }

  fun rewind() {
    exoPlayer.seekTo(-2000)
  }

  fun isPlaying(): Boolean {
    if (exoPlayer.isPlaying && exoPlayer.playbackState == Player.STATE_READY)
      exoPlayer.pause()
    else
      exoPlayer.play()
    return exoPlayer.isPlaying
  }

  fun setOnSongChangeListener(onSongChangeListener: OnSongChangeListener) {
    this.onSongChangeListener = onSongChangeListener
  }

  inner class MusicServiceBinder : Binder() {
    fun getServiceInstance(): MusicService {
      return this@MusicService
    }
  }
}