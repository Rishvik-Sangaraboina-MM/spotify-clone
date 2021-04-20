package com.example.baseapp.injection.module

import com.example.data.repository.AuthRepo
import com.example.data.repository.IMusicLocalSource
import com.example.data.repository.IMusicRemoteSource
import com.example.data.repository.MusicRepo
import com.example.domain.repository.IAuthRepo
import com.example.domain.repository.IMusicRepo
import com.example.domain.source.IAuthLocalSource
import com.example.domain.source.IAuthRemoteSource
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {
  @Provides
  fun provideAuthRepo(
    authLocalSource: IAuthLocalSource,
    authRemoteSource: IAuthRemoteSource
  ): IAuthRepo {
    return AuthRepo(authLocalSource, authRemoteSource)
  }

  @Provides
  fun provideMusicRepo(
    musicRemoteSource: IMusicRemoteSource,
    musicLocalSource: IMusicLocalSource
  ): IMusicRepo {
    return MusicRepo(musicRemoteSource, musicLocalSource)
  }
}