package com.example.baseapp.util

import android.support.v4.media.MediaMetadataCompat

interface OnSongChangeListener {
  fun onSongChange(mediaMetadataCompat: MediaMetadataCompat)
}