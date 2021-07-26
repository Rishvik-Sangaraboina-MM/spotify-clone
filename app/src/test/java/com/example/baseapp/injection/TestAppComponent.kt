package com.example.baseapp.injection

import android.content.Context
import com.example.baseapp.TestApplication
import com.example.baseapp.injection.module.PreferenceModule
import com.example.baseapp.injection.module.RepositoryModule
import com.example.baseapp.injection.module.SourceModule
import com.example.baseapp.injection.module.UseCaseModule
import com.example.baseapp.injection.qualifiers.ApplicationContext
import com.example.baseapp.usecaseTest.FetchMusicUseCaseTest
import com.example.baseapp.usecaseTest.IsUserLoggedInUseCaseTest
import com.example.baseapp.usecaseTest.LoginUserUseCaseTest
import com.example.baseapp.usecaseTest.LogoutUserUseCaseTest
import com.example.baseapp.usecaseTest.SignUpUserUseCaseTest
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    FakeNetworkModule::class,
    AndroidInjectionModule::class,
    SourceModule::class,
    RepositoryModule::class,
    UseCaseModule::class,
    PreferenceModule::class
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

  fun inject(signUpUserUseCaseTest: SignUpUserUseCaseTest)

  fun inject(fetchMusicUseCaseTest: FetchMusicUseCaseTest)
}