package com.example.baseapp.injection.module

import com.example.domain.repository.IAuthRepo
import com.example.domain.usecase.auth.IsUserLoggedInUseCase
import com.example.domain.usecase.auth.LoginUserUseCase
import com.example.domain.usecase.auth.LogoutUserUseCase
import com.example.domain.usecase.auth.SignUpUserUseCase
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

  @Provides
  fun provideLoginUserUseCase(authRepo: IAuthRepo): LoginUserUseCase {
    return LoginUserUseCase(authRepo)
  }

  @Provides
  fun provideLogoutUserUseCase(authRepo: IAuthRepo): LogoutUserUseCase {
    return LogoutUserUseCase(authRepo)
  }

  @Provides
  fun provideIsUserLoggedInUseCase(authRepo: IAuthRepo): IsUserLoggedInUseCase {
    return IsUserLoggedInUseCase(authRepo)
  }

  @Provides
  fun provideSignUpUserUseCase(authRepo: IAuthRepo): SignUpUserUseCase {
    return SignUpUserUseCase(authRepo)
  }
}