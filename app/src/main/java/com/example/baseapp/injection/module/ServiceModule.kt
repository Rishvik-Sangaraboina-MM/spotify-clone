package com.example.baseapp.injection.module

import android.content.Context
import com.example.baseapp.injection.qualifiers.ApplicationContext
import com.google.android.exoplayer2.C
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.audio.AudioAttributes
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.util.Util
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ServiceModule {

  @Provides
  fun provideAudioAttributes(): AudioAttributes {
    return AudioAttributes.Builder()
      .setContentType(C.CONTENT_TYPE_MUSIC)
      .setUsage(C.USAGE_MEDIA)
      .build()
  }

  @Provides
  @Singleton
  fun provideExoPlayer(
    @ApplicationContext context: Context,
    audioAttributes: AudioAttributes
  ): ExoPlayer {
    return SimpleExoPlayer.Builder(context).build().apply {
      setAudioAttributes(audioAttributes, true)
      setHandleAudioBecomingNoisy(true)
    }
  }

  @Provides
  fun provideDataSourceFactory(@ApplicationContext context: Context): DefaultDataSourceFactory {
    return DefaultDataSourceFactory(context, Util.getUserAgent(context, "Spotify App"))
  }
}