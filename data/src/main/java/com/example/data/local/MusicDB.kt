package com.example.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.local.dao.SongDao
import com.example.data.local.model.SongEntity

@Database(entities = [SongEntity::class], version = 1)
abstract class MusicDB : RoomDatabase() {
  abstract fun getSongDao(): SongDao
}