package com.example.baseapp

import com.example.baseapp.injection.DaggerTestAppComponent
import com.example.baseapp.injection.TestAppComponent
import dagger.android.DaggerApplication

class TestApplication : DaggerApplication() {

  private val component: TestAppComponent by lazy {
    DaggerTestAppComponent.factory().create(this, applicationContext) as TestAppComponent
  }

  override fun applicationInjector() = component

  fun provideComponent() = component
}