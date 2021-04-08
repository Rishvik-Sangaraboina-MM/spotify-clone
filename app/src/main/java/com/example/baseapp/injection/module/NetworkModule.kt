package com.example.baseapp.injection.module

import com.example.baseapp.BuildConfig
import com.example.baseapp.injection.qualifiers.AuthBaseUrl
import com.example.baseapp.injection.qualifiers.AuthRetrofit
import com.example.baseapp.injection.qualifiers.MusicBaseUrl
import com.example.baseapp.injection.qualifiers.MusicRetrofit
import com.example.baseapp.util.AppConstants
import com.example.data.remote.api.AuthApi
import com.example.data.remote.api.MusicApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

  @Provides
  @Singleton
  fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor {
    return HttpLoggingInterceptor().apply {
      level = if (BuildConfig.DEBUG) {
        HttpLoggingInterceptor.Level.BODY
      } else {
        HttpLoggingInterceptor.Level.NONE
      }
    }
  }

  @Provides
  @Singleton
  fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
    return OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()
  }

  @Provides
  @AuthBaseUrl
  fun provideAuthBaseUrl() = AppConstants.AUTH_BASE_URL

  @Provides
  @MusicBaseUrl
  fun provideMusicBaseUrl() = AppConstants.MUSIC_BASE_URL

  @Provides
  @Singleton
  @AuthRetrofit
  fun provideAuthRetrofitInstance(
    @AuthBaseUrl baseUrl: String,
    okHttpClient: OkHttpClient
  ): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl(baseUrl)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  @MusicRetrofit
  fun provideMusicRetrofitInstance(
    @MusicBaseUrl baseUrl: String,
    okHttpClient: OkHttpClient
  ): Retrofit {
    return Retrofit.Builder()
      .client(okHttpClient)
      .baseUrl(baseUrl)
      .addConverterFactory(GsonConverterFactory.create())
      .build()
  }

  @Provides
  @Singleton
  fun provideAuthApi(@AuthRetrofit retrofit: Retrofit): AuthApi {
    return retrofit.create(AuthApi::class.java)
  }

  @Provides
  @Singleton
  fun provideMusicApi(@MusicRetrofit retrofit: Retrofit): MusicApi {
    return retrofit.create(MusicApi::class.java)
  }
}