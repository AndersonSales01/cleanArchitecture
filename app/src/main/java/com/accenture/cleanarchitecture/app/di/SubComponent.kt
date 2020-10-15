package com.accenture.cleanarchitecture.app.di

import com.accenture.cleanarchitecture.app.features.pullrequest.ui.view.PullRequestActivity
import com.accenture.cleanarchitecture.app.features.repository.ui.view.RepositoryActivity
import com.example.anderson.projectdagger2.di.ViewModelBuilderModule

import dagger.Subcomponent

@Subcomponent(modules = [ViewModelModule::class,UseCasesModule::class, ViewModelBuilderModule::class])
interface SubComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(): SubComponent
    }
    fun inject(activity: RepositoryActivity)
    fun inject(activity: PullRequestActivity)
}