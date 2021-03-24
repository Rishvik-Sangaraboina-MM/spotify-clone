package com.example.baseapp.ui.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle

inline fun <reified T : Activity> Context.openActivityWithSingleTop(extras: Bundle.() -> Unit = {}) {
  val intent = Intent(this, T::class.java)
  intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
  intent.putExtras(Bundle().apply(extras))
  startActivity(intent)
}

inline fun <reified T : Activity> Context.openActivity(extras: Bundle.() -> Unit = {}) {
  val intent = Intent(this, T::class.java)
  intent.putExtras(Bundle().apply(extras))
  startActivity(intent)
}