package com.example.baseapp.ui.home

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.baseapp.R
import com.example.baseapp.databinding.ActivityHomeBinding
import com.example.baseapp.service.MusicService
import com.example.baseapp.ui.base.BaseActivity
import com.example.baseapp.util.OnSongChangeListener
import com.example.baseapp.util.SongItemClickListener
import com.example.domain.entity.Song
import javax.inject.Inject

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeVM>(),
  SongItemClickListener,
  OnSongChangeListener {

  override fun getViewModelClass(): Class<HomeVM> = HomeVM::class.java

  override fun getLayoutId(): Int = R.layout.activity_home

  @Inject
  lateinit var musicService: MusicService

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    initUI()
    addListeners()
  }

  private fun initUI() {
    binding.apply {
      bottomNavigationView.setupWithNavController(
        findNavController(R.id.nav_host_fragment_container)
      )
    }
  }

  private fun addListeners() {
    with(binding) {
      playPause.setOnClickListener {
        if (musicService.playPause())
          Glide.with(it.context).load(R.drawable.ic_pause).into(playPause)
        else
          Glide.with(it.context).load(R.drawable.ic_play).into(playPause)
      }
      skipNext.setOnClickListener {
        musicService.skipNext()
      }
      skipPrevious.setOnClickListener {
        musicService.skipPrevious()
      }
      forward.setOnClickListener {
        musicService.forward()
      }
      rewind.setOnClickListener {
        musicService.rewind()
      }
    }
  }

  override fun onSongItemClick(
    songs: List<Song>,
    index: Int
  ) {
    musicService.setOnSongChangeListener(this)
    musicService.preparePlayer(songs, index, true)
    binding.motionLayout.transitionToEnd()
  }

  override fun onSongChange(songResponse: Song?) {
    binding.song = songResponse
  }

  override fun onBackPressed() {
    if (binding.motionLayout.currentState == binding.motionLayout.endState)
      binding.motionLayout.transitionToStart()
    else
      super.onBackPressed()
  }
}