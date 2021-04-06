package com.example.baseapp.service

import android.content.Context
import android.support.v4.media.session.MediaControllerCompat

class MusicServiceConnection(private val context: Context) {
  lateinit var mediaController: MediaControllerCompat

  private val transportControls
    get() = mediaController.transportControls
}