package com.example.baseapp.ui.home

import android.os.Bundle
import android.support.v4.media.MediaMetadataCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.example.baseapp.R
import com.example.baseapp.databinding.ActivityHomeBinding
import com.example.baseapp.ui.base.BaseActivity
import com.example.baseapp.util.SongItemClickListener

class HomeActivity : BaseActivity<ActivityHomeBinding, HomeVM>(), SongItemClickListener {

  override fun getViewModelClass(): Class<HomeVM> = HomeVM::class.java

  override fun getLayoutId(): Int = R.layout.activity_home

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
    musicService.preparePlayer(songs, index, true)
  }
}