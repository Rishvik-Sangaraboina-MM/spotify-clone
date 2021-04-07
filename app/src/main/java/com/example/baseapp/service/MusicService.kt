package com.example.baseapp.service

import com.example.baseapp.util.OnSongChangeListener
import com.example.baseapp.util.asConcatenatingMediaSource
import com.example.domain.entity.SongResponse
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player
import com.google.android.exoplayer2.Player.EventListener
import com.google.android.exoplayer2.source.TrackGroupArray
import com.google.android.exoplayer2.trackselection.TrackSelectionArray
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MusicService @Inject constructor(
  private val dataSourceFactory: DefaultDataSourceFactory,
  private val exoPlayer: ExoPlayer,
) : EventListener {

  private lateinit var onSongChangeListener: OnSongChangeListener

  private var currentPlayList: List<SongResponse>? = null

  private var currentSongIndex: Int? = null

  override fun onTracksChanged(
    trackGroups: TrackGroupArray,
    trackSelections: TrackSelectionArray
  ) {
    super.onTracksChanged(trackGroups, trackSelections)
    onSongChangeListener.onSongChange(currentPlayList?.get(exoPlayer.currentWindowIndex))
  }

  fun preparePlayer(
    songs: List<SongResponse>,
    index: Int,
    playNow: Boolean
  ) {
    if (currentPlayList != songs) {
      exoPlayer.setMediaSource(asConcatenatingMediaSource(dataSourceFactory, songs))
      currentPlayList = songs
      currentSongIndex = null
    }
    if (currentSongIndex != index) {
      currentSongIndex = index
      exoPlayer.seekTo(currentSongIndex!!, 0L)
      exoPlayer.addListener(this)
      exoPlayer.prepare()
      exoPlayer.playWhenReady = playNow
    }
  }

  fun skipNext() {
    exoPlayer.next()
  }

  fun skipPrevious() {
    exoPlayer.previous()
  }

  fun forward() {
    exoPlayer.seekTo(5000)
  }

  fun rewind() {
    exoPlayer.seekTo(-5000)
  }

  fun playPause(): Boolean {
    if (exoPlayer.isPlaying && exoPlayer.playbackState == Player.STATE_READY)
      exoPlayer.pause()
    else
      exoPlayer.play()
    return exoPlayer.isPlaying
  }

  fun setOnSongChangeListener(onSongChangeListener: OnSongChangeListener) {
    this.onSongChangeListener = onSongChangeListener
  }
}