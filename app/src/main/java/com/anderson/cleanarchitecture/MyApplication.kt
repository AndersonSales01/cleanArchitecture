package com.anderson.cleanarchitecture

import android.app.Application
import com.anderson.cleanarchitecture.di.ApplicationComponent
import com.anderson.cleanarchitecture.di.DaggerApplicationComponent


class MyApplication : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.factory()
            .create(this)
    }
}