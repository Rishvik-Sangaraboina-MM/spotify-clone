package com.example.baseapp.injection.module

import android.content.Context
import androidx.room.Room
import com.example.baseapp.injection.qualifiers.ApplicationContext
import com.example.baseapp.util.AppConstants
import com.example.data.local.MusicDB
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {

  @Provides
  fun provideMusicDB(@ApplicationContext context: Context): MusicDB {
    return Room.databaseBuilder(context, MusicDB::class.java, AppConstants.MUSIC_DB_NAME).build()
  }
}