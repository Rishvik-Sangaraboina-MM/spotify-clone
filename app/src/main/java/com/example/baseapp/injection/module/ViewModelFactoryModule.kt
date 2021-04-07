package com.example.baseapp.injection.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.baseapp.injection.scope.ViewModelScope
import com.example.baseapp.ui.auth.login.LoginVM
import com.example.baseapp.ui.auth.signup.SignUpVM
import com.example.baseapp.ui.home.HomeVM
import com.example.baseapp.ui.home.account.AccountVM
import com.example.baseapp.ui.home.music.MusicVM
import com.example.baseapp.ui.home.search.SearchVM
import com.example.baseapp.util.ViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelFactoryModule {

  @Binds
  abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

  @Binds
  @IntoMap
  @ViewModelScope(HomeVM::class)
  abstract fun bindHomeVM(homeVM: HomeVM): ViewModel

  @Binds
  @IntoMap
  @ViewModelScope(LoginVM::class)
  abstract fun bindLoginVM(loginVM: LoginVM): ViewModel

  @Binds
  @IntoMap
  @ViewModelScope(SignUpVM::class)
  abstract fun bindSignUpVM(signUpVM: SignUpVM): ViewModel

  @Binds
  @IntoMap
  @ViewModelScope(AccountVM::class)
  abstract fun bindAccountVM(accountVM: AccountVM): ViewModel

  @Binds
  @IntoMap
  @ViewModelScope(MusicVM::class)
  abstract fun bindMusicVM(musicVM: MusicVM): ViewModel

  @Binds
  @IntoMap
  @ViewModelScope(SearchVM::class)
  abstract fun bindSearchVM(searchVM: SearchVM): ViewModel
}