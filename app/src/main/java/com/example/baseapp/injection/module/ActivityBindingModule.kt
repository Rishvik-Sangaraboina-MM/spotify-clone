package com.example.baseapp.injection.module

import com.example.baseapp.injection.scope.ActivityScope
import com.example.baseapp.ui.auth.login.LoginActivity
import com.example.baseapp.ui.home.HomeActivity
import com.example.baseapp.ui.home.HomeActivityModule
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBindingModule {

  @ActivityScope
  @ContributesAndroidInjector(modules = [HomeActivityModule::class])
  abstract fun bindHomeActivity(): HomeActivity

  @ActivityScope
  @ContributesAndroidInjector
  abstract fun bindLoginActivity(): LoginActivity
}