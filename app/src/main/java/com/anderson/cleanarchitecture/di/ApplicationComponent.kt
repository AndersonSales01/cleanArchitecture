package com.anderson.cleanarchitecture.di

import android.content.Context
import com.anderson.cleanarchitecture.app.di.SubComponent
import dagger.BindsInstance
import dagger.Component
import dagger.Module
import javax.inject.Singleton


//Onde sera iniciado todas as dependencias do dagger
@Singleton
@Component(modules = [RepositoryModule::class,SubcomponentsModule::class,NetWorkModule::class,DataBaseModule::class])
interface ApplicationComponent {
    // Esse factory serve para passar contexto da aplicacao e o BindsInstance permite dar um "this", entao na minha classe application vai ser passado contexto
    // da aplicação para que possa ser usado nas classes.
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): ApplicationComponent
    }

    fun uiComponent(): SubComponent.Factory
}

@Module(subcomponents = [SubComponent::class])
object SubcomponentsModule