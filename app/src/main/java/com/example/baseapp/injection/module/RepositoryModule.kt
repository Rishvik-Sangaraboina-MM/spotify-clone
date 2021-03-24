package com.example.baseapp.injection.module

import com.example.baseapp.injection.scope.ActivityScope
import com.example.data.repository.AuthRepo
import com.example.data.source.AuthLocalSource
import com.example.data.source.AuthRemoteSource
import com.example.domain.repository.IAuthRepo
import com.example.domain.source.IAuthLocalSource
import com.example.domain.source.IAuthRemoteSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {
  @Provides
  fun provideAuthRepo(authLocalSource: IAuthLocalSource,authRemoteSource: IAuthRemoteSource) : IAuthRepo{
    return AuthRepo(authLocalSource,authRemoteSource)
  }
}