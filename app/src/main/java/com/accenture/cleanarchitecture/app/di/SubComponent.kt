package com.accenture.cleanarchitecture.app.di

import com.accenture.cleanarchitecture.app.features.pullrequest.ui.view.PullRequestActivity
import com.accenture.cleanarchitecture.app.features.repository.ui.view.RepositoryActivity

import dagger.Subcomponent

@Subcomponent(modules = [ViewModelModule::class,UseCasesModule::class])
interface SubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SubComponent
    }
    fun inject(activity: RepositoryActivity)
    fun inject(activity: PullRequestActivity)
}