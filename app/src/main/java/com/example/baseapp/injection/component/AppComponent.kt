package com.example.baseapp.injection.component

import android.content.Context
import com.example.baseapp.injection.module.ActivityBindingModule
import com.example.baseapp.injection.module.AppModule
import com.example.baseapp.injection.module.NetworkModule
import com.example.baseapp.injection.module.PreferenceModule
import com.example.baseapp.injection.module.RepositoryModule
import com.example.baseapp.injection.module.ServiceModule
import com.example.baseapp.injection.module.SourceModule
import com.example.baseapp.injection.module.UseCaseModule
import com.example.baseapp.injection.module.ViewModelFactoryModule
import com.example.baseapp.injection.qualifiers.ApplicationContext
import com.example.baseapp.service.MusicService
import com.example.baseapp.util.BaseApplication
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
  modules = [
    AndroidSupportInjectionModule::class,
    AppModule::class,
    ActivityBindingModule::class,
    ViewModelFactoryModule::class,
    RepositoryModule::class,
    SourceModule::class,
    UseCaseModule::class,
    NetworkModule::class,
    PreferenceModule::class,
    ServiceModule::class
  ]
)
interface AppComponent : AndroidInjector<BaseApplication> {

  @Component.Factory
  interface Factory {
    fun create(
      @BindsInstance application: BaseApplication,
      @BindsInstance @ApplicationContext context: Context
    ): AppComponent
  }

  fun inject(musicService: MusicService)
}