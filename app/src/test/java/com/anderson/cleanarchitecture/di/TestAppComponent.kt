package com.anderson.cleanarchitecture.di

import android.content.Context
import com.anderson.cleanarchitecture.app.di.SubComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class,SubcomponentsModule::class,NetWorkModule::class,DataBaseModule::class])
interface TestAppComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    fun uiComponent(): SubComponent.Factory

}

@Module(subcomponents = [SubComponent::class])
object SubcomponentsModule