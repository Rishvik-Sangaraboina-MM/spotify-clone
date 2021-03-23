package com.example.baseapp.injection.module

import com.example.domain.repository.IAuthRepo
import com.example.domain.usecase.auth.IsUserLoggedInUseCase
import com.example.domain.usecase.auth.LoginUserUseCase
import com.example.domain.usecase.auth.LogoutUserUseCase
import com.example.domain.usecase.auth.SignUpUserUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UsecaseModule {

  @Provides
  @Singleton
  fun provideLoginUserUseCase(authRepo: IAuthRepo) : LoginUserUseCase{
    return LoginUserUseCase(authRepo)
  }

  @Provides
  @Singleton
  fun provideLogoutUserUseCase(authRepo: IAuthRepo) : LogoutUserUseCase{
    return LogoutUserUseCase(authRepo)
  }

  @Provides
  @Singleton
  fun provideIsUserLoggedInUseCase(authRepo: IAuthRepo) : IsUserLoggedInUseCase{
    return IsUserLoggedInUseCase(authRepo)
  }

  @Provides
  @Singleton
  fun provideSignUpUserUseCase(authRepo: IAuthRepo) : SignUpUserUseCase{
    return SignUpUserUseCase(authRepo)
  }
}