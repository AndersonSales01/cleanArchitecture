package com.accenture.cleanarchitecture.app.di

import androidx.lifecycle.ViewModel
import com.accenture.cleanarchitecture.app.features.pullrequest.viewmodel.PullRequestViewModel
import com.accenture.cleanarchitecture.app.features.repository.viewmodel.RepositoryViewModel
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
}