package com.example.baseapp.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.IllegalArgumentException
import javax.inject.Inject
import javax.inject.Provider
import kotlin.concurrent.thread

@Suppress("UNCHECKED_CAST")
class ViewModelFactory @Inject constructor(private val map: Map<Class<out ViewModel>, @JvmSuppressWildcards Provider<ViewModel>>) :
  ViewModelProvider.Factory {
  override fun <T : ViewModel?> create(modelClass: Class<T>): T {
    var creator : Provider<ViewModel>? = map[modelClass]
    if(creator==null)
      for((key,value) in map)
        if(modelClass.isAssignableFrom(key)){
          creator = value
        }
    if(creator == null)
      throw IllegalArgumentException("unknown model class $modelClass")
    return creator.get() as T
  }
}