package com.accenture.cleanarchitecture.presentation.common

import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    abstract fun initialize()
    abstract fun initViews()
    abstract fun observables()
}