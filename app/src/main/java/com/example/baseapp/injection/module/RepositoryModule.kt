package com.example.baseapp.injection.module

import com.example.data.repository.AuthRepo
import com.example.domain.repository.IAuthRepo
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
}