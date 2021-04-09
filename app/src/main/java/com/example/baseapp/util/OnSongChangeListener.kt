package com.example.baseapp.util

import com.example.domain.entity.Song

interface OnSongChangeListener {
  fun onSongChange(songResponse: Song?)
}