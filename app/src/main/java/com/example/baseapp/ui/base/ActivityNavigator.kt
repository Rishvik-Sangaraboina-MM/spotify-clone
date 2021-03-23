package com.example.baseapp.ui.base

import android.app.Activity
import android.content.Intent

object ActivityNavigator {

  fun startActivity(
    activity: Activity,
    activityClass: Class<out Activity>
  ) {
    val intent = Intent(activity, activityClass)
    activity.startActivity(intent)
  }

  fun startActivityWithSingleTop(
    activity: Activity,
    activityClass: Class<out Activity>
  ) {
    val intent = Intent(activity, activityClass)
    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
    activity.startActivity(intent)
  }

}