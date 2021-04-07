package com.example.baseapp.service

import android.support.v4.media.MediaMetadataCompat
import com.example.baseapp.util.OnSongChangeListener
import com.example.baseapp.util.asConcatenatingMediaSource
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.Player.EventListener
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import javax.inject.Inject

class MusicService @Inject constructor(
  private val dataSourceFactory: DefaultDataSourceFactory,
  private val exoPlayer: ExoPlayer,
) : EventListener {

  private lateinit var onSongChangeListener: OnSongChangeListener

  fun preparePlayer(
    songs: List<MediaMetadataCompat>,
    index: Int,
    playNow: Boolean
  ) {
    onSongChangeListener.onSongChange(songs[0])
    exoPlayer.setMediaSource(asConcatenatingMediaSource(dataSourceFactory, songs))
    exoPlayer.seekTo(index, 0L)
    exoPlayer.addListener(this)
    exoPlayer.prepare()
    exoPlayer.playWhenReady = playNow
  }

  fun setOnSongChangeListener(onSongChangeListener: OnSongChangeListener) {
    this.onSongChangeListener = onSongChangeListener
  }
}