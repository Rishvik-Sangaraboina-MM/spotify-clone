package com.example.baseapp.injection.module

import com.example.baseapp.injection.scope.ActivityScope
import com.example.domain.repository.IAuthRepo
import com.example.domain.usecase.auth.IsUserLoggedInUseCase
import com.example.domain.usecase.auth.LoginUserUseCase
import com.example.domain.usecase.auth.LogoutUserUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UsecaseModule {

  @Provides
  @ActivityScope
  fun provideLoginUserUseCase(authRepo: IAuthRepo) : LoginUserUseCase{
    return LoginUserUseCase(authRepo)
  }

  @Provides
  @ActivityScope
  fun provideLogoutUserUseCase(authRepo: IAuthRepo) : LogoutUserUseCase{
    return LogoutUserUseCase(authRepo)
  }

  @Provides
  @ActivityScope
  fun provideIsUserLoggedInUseCase(authRepo: IAuthRepo) : IsUserLoggedInUseCase{
    return IsUserLoggedInUseCase(authRepo)
  }
}