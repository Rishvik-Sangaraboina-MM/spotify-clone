package com.example.baseapp.ui.home

import androidx.lifecycle.ViewModel
import com.example.baseapp.injection.scope.FragmentScoped
import com.example.baseapp.injection.scope.ViewModelKey
import com.example.baseapp.ui.home.account.AccountFragment
import com.example.baseapp.ui.home.account.AccountVM
import com.example.baseapp.ui.home.music.MusicFragment
import com.example.baseapp.ui.home.music.MusicVM
import com.example.baseapp.ui.home.search.SearchFragment
import com.example.baseapp.ui.home.search.SearchVM
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.multibindings.IntoMap

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

  @Binds
  @IntoMap
  @ViewModelKey(AccountVM::class)
  abstract fun bindAccountVM(accountVM: AccountVM): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(SearchVM::class)
  abstract fun bindSearchVM(searchVM: SearchVM): ViewModel

  @Binds
  @IntoMap
  @ViewModelKey(MusicVM::class)
  abstract fun bindMusicVM(musicVM: MusicVM): ViewModel
}