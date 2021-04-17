package com.example.baseapp.ui.home

import android.content.ComponentName
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.util.Log
import androidx.core.view.isVisible
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.example.baseapp.R
import com.example.baseapp.databinding.ActivityHomeBinding
import com.example.baseapp.service.MusicService
import com.example.baseapp.service.MusicService.MusicServiceBinder
import com.example.baseapp.ui.base.BaseActivity
import com.example.baseapp.util.OnSongChangeListener
import com.example.baseapp.util.SongItemClickListener
import com.example.domain.entity.Song

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeVM>(),
  SongItemClickListener,
  OnSongChangeListener {

  override fun getViewModelClass(): Class<HomeVM> = HomeVM::class.java

  override fun getLayoutId(): Int = R.layout.activity_home

  private val serviceConnection: ServiceConnection = object : ServiceConnection {
    override fun onServiceConnected(
      name: ComponentName?,
      service: IBinder?
    ) {
      Log.i("Service", "Connected")
      musicService = (service as MusicServiceBinder)?.getServiceInstance()
    }

    override fun onServiceDisconnected(name: ComponentName?) {
      Log.i("Service", "Disconnected")
    }
  }

  var musicService: MusicService? = null

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
    binding.linearLayout.isVisible = false
  }

  private fun addListeners() {
    with(binding) {
      playPause.setOnClickListener {
        if (musicService?.isPlaying() == true)
          Glide.with(it.context).load(R.drawable.ic_pause).into(playPause)
        else
          Glide.with(it.context).load(R.drawable.ic_play).into(playPause)
      }
      skipNext.setOnClickListener {
        musicService?.skipNext()
      }
      skipPrevious.setOnClickListener {
        musicService?.skipPrevious()
      }
      forward.setOnClickListener {
        musicService?.forward()
      }
      rewind.setOnClickListener {
        musicService?.rewind()
      }
    }
  }

  override fun onSongItemClick(
    songs: List<Song>,
    index: Int
  ) {
    musicService?.setOnSongChangeListener(this)
    musicService?.preparePlaylist(songs, index, true)
    musicService?.let {
      binding.linearLayout.isVisible = true
      binding.motionLayout.transitionToEnd()
    } ?: startService()
  }

  private fun startService() {
    val intent = Intent(this, MusicService::class.java)
    startService(intent)
    bindService(intent, serviceConnection, BIND_AUTO_CREATE)
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