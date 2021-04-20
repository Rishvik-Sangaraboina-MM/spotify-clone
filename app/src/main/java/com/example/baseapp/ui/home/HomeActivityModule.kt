package com.example.baseapp.ui.home

import com.example.baseapp.injection.scope.FragmentScoped
import com.example.baseapp.ui.home.account.AccountFragment
import com.example.baseapp.ui.home.music.MusicFragment
import com.example.baseapp.ui.home.search.SearchFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class HomeActivityModule {

  @FragmentScoped
  @ContributesAndroidInjector
  abstract fun bindAccountFragment(): AccountFragment

  @FragmentScoped
  @ContributesAndroidInjector
  abstract fun bindMusicFragment(): MusicFragment

  @FragmentScoped
  @ContributesAndroidInjector
  abstract fun bindSearchFragment(): SearchFragment

}