package com.anderson.cleanarchitecture.app.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.anderson.cleanarchitecture.MyApplication
import com.anderson.cleanarchitecture.app.di.SubComponent

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