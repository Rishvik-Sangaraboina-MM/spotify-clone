package com.example.baseapp.ui.home

import android.os.Bundle
import android.support.v4.media.MediaMetadataCompat
import android.util.Log
import androidx.core.net.toUri
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.baseapp.R
import com.example.baseapp.databinding.ActivityHomeBinding
import com.example.baseapp.ui.base.BaseActivity
import com.example.baseapp.util.SongItemClickListener
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.source.ConcatenatingMediaSource
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeVM>(), SongItemClickListener {

  override fun getViewModelClass(): Class<HomeVM> = HomeVM::class.java

  override fun getLayoutId(): Int = R.layout.activity_home

  @Inject
  lateinit var dataSourceFactory: DefaultDataSourceFactory

  @Inject
  lateinit var exoPlayer: ExoPlayer

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initUI()
  }

  private fun initUI() {
    binding.apply {
      bottomNavigationView.setupWithNavController(
        findNavController(R.id.nav_host_fragment_container)
      )
    }
  }

  override fun onSongItemClick(
    songs: List<MediaMetadataCompat>,
    index: Int
  ) {
    preparePlayer(songs, index, true)
  }

  fun preparePlayer(
    songs: List<MediaMetadataCompat>,
    index: Int,
    playNow: Boolean
  ) {
    exoPlayer.setMediaSource(asConcatenatingMediaSource(songs))
    exoPlayer.seekTo(index, 0L)
    exoPlayer.prepare()
    exoPlayer.playWhenReady = playNow
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