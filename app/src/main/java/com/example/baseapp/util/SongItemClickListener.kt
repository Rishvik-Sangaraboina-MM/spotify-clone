package com.example.baseapp.util

import android.support.v4.media.MediaMetadataCompat

interface SongItemClickListener {
  fun onSongItemClick(
    songs: List<MediaMetadataCompat>,
    index: Int
  )
}