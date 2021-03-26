package com.example.baseapp.injection

import android.content.Context
import com.example.baseapp.TestApplication
import com.example.baseapp.injection.qualifiers.ApplicationContext
import com.example.baseapp.usecaseTest.IsUserLoggedInUseCaseTest
import com.example.baseapp.usecaseTest.LoginUserUseCaseTest
import com.example.baseapp.usecaseTest.LogoutUserUseCaseTest
import com.example.baseapp.usecaseTest.SignInUserUseCase
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    FakeNetworkModule::class,
    AndroidInjectionModule::class
  ]
)
interface TestAppComponent : AndroidInjector<TestApplication> {

  @Component.Factory
  interface Factory {
    fun create(
      @BindsInstance application: TestApplication,
      @BindsInstance @ApplicationContext context: Context
    ): TestAppComponent
  }

  fun inject(isUserLoggedInUseCaseTest: IsUserLoggedInUseCaseTest)

  fun inject(loginUserUseCaseTest: LoginUserUseCaseTest)

  fun inject(logoutUserUseCaseTest: LogoutUserUseCaseTest)

  fun inject(signInUserUseCase: SignInUserUseCase)
}