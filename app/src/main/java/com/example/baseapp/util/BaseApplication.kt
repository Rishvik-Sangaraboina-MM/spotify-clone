package com.example.baseapp.util

import com.example.baseapp.injection.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {

  private val component by lazy { DaggerAppComponent.factory().create(this, applicationContext) }
  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return component
  }

  fun provideComponent() = component
}