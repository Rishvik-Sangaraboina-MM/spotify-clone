package com.example.baseapp.util

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.baseapp.R

object BindingAdapter {
  @JvmStatic
  @BindingAdapter("visibility")
  fun setVisibility(
    view: View,
    visible: Boolean
  ) {
    view.visibility = if (visible) View.VISIBLE else View.GONE
  }

  @JvmStatic
  @BindingAdapter("imgSrcUrl")
  fun setImage(
    imageView: ImageView,
    url: String?
  ) {
    Glide.with(imageView.context)
      .load(url)
      .fitCenter()
      .placeholder(R.drawable.exo_styled_controls_audiotrack)
      .into(imageView)
  }
}