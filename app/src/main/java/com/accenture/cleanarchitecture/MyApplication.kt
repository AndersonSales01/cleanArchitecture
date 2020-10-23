package com.accenture.cleanarchitecture

import android.app.Application
import com.accenture.cleanarchitecture.di.ApplicationComponent
import com.accenture.cleanarchitecture.di.DaggerApplicationComponent


class MyApplication : Application() {

    lateinit var appComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerApplicationComponent.factory()
            .create(this)
    }
}