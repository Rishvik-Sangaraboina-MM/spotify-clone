package com.example.baseapp.util

import android.view.View
import androidx.databinding.BindingAdapter

object BindingAdapter {
  @JvmStatic
  @BindingAdapter("visibility")
  fun setVisibility(
    view: View,
    visible: Boolean
  ) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
  }
}