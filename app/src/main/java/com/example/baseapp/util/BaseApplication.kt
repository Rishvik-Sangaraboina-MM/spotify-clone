package com.example.baseapp.util

import android.app.NotificationChannel
import android.app.NotificationManager
import com.example.baseapp.R
import com.example.baseapp.injection.component.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class BaseApplication : DaggerApplication() {

  val component by lazy { DaggerAppComponent.factory().create(this, applicationContext) }

  override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
    return component
  }

  override fun onCreate() {
    super.onCreate()
    createNotificationChannel()
  }

  private fun createNotificationChannel(): NotificationChannel {
    val name = getString(R.string.channel_name)
    val descriptionText = getString(R.string.channel_description)
    val mChannel = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
      val importance = NotificationManager.IMPORTANCE_DEFAULT
      NotificationChannel(AppConstants.CHANNEL_ID, name, importance)
    } else {
      TODO("VERSION.SDK_INT < O")
    }
    mChannel.description = descriptionText
    val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
    notificationManager.createNotificationChannel(mChannel)
    return mChannel
  }
}