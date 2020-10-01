package com.accenture.cleanarchitecture.app.common

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun initialize()
    abstract fun initViews()
    abstract fun observables()
}