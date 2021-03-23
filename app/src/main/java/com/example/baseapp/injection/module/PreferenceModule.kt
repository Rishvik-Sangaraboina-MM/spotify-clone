package com.example.baseapp.injection.module

import android.content.Context
import android.content.SharedPreferences
import com.example.baseapp.injection.qualifiers.ApplicationContext
import com.example.baseapp.util.AppConstants
import com.example.data.local.TokenManager
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class PreferenceModule {

  @Provides
  @Singleton
  fun provideSharedPreference(@ApplicationContext context: Context) : SharedPreferences{
    return context.getSharedPreferences(AppConstants.APP_PREFERENCE_NAME,Context.MODE_PRIVATE)
  }

  @Provides
  @Singleton
  fun provideTokenManager(sharedPreferences: SharedPreferences) : TokenManager{
    return TokenManager(sharedPreferences)
  }

}