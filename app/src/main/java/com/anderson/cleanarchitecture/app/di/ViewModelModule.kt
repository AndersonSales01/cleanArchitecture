package com.anderson.cleanarchitecture.app.di

import androidx.lifecycle.ViewModel
import com.anderson.cleanarchitecture.app.features.login.viewmodel.LoginViewModel
import com.anderson.cleanarchitecture.app.features.main.model.MainViewModel
import com.anderson.cleanarchitecture.app.features.pullrequest.viewmodel.PullRequestViewModel
import com.anderson.cleanarchitecture.app.features.registeruser.viewmodel.RegisterLoginViewModel
import com.anderson.cleanarchitecture.app.features.repository.viewmodel.RepositoryViewModel
import com.example.anderson.projectdagger2.di.ViewModelKey
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
interface ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(RepositoryViewModel::class)
    fun bindGitHubViewModel(viewModel: RepositoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(PullRequestViewModel::class)
    fun bindPullRequestViewModel(viewModel: PullRequestViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegisterLoginViewModel::class)
    fun bindRegisterLoginViewModel(viewModel: RegisterLoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    fun bindMainViewModel(viewModel: MainViewModel): ViewModel
}