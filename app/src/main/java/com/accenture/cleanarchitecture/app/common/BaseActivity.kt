package com.accenture.cleanarchitecture.app.common

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.accenture.cleanarchitecture.MyApplication
import com.accenture.cleanarchitecture.app.di.SubComponent

abstract class BaseActivity : AppCompatActivity() {

    lateinit var subComponent: SubComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subComponent = (applicationContext as MyApplication).appComponent.uiComponent().create()
    }

    abstract fun initialize()
    abstract fun initViews()
    abstract fun observables()
}