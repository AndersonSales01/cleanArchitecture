package com.anderson.cleanarchitecture.app.di

import com.anderson.cleanarchitecture.app.features.login.ui.LoginActivity
import com.anderson.cleanarchitecture.app.features.main.ui.MainActivity
import com.anderson.cleanarchitecture.app.features.pullrequest.ui.view.PullRequestActivity
import com.anderson.cleanarchitecture.app.features.registeruser.ui.RegisterLoginActivity
import com.anderson.cleanarchitecture.app.features.repository.ui.view.RepositoryActivity
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
    fun inject(activity: RegisterLoginActivity)
    fun inject(activity: LoginActivity)
    fun inject(activity: MainActivity)
}