package com.anderson.cleanarchitecture.app.features.main.ui

import android.content.Intent
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.anderson.cleanarchitecture.R
import com.anderson.cleanarchitecture.app.common.BaseActivity
import com.anderson.cleanarchitecture.app.features.login.ui.LoginActivity
import com.anderson.cleanarchitecture.app.features.main.model.MainViewModel
import com.anderson.cleanarchitecture.app.features.repository.ui.view.RepositoryActivity
import javax.inject.Inject

class MainActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[MainViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //hiding title bar of this activity
        window.requestFeature(Window.FEATURE_NO_TITLE)
        //making this activity full screen
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        setContentView(R.layout.activity_main)
        initialize()
    }

    override fun initialize() {
        subComponent.inject(this)

        initViews()

        observables()

        viewModel.verifyUserLogged()
    }

    override fun initViews() {

    }

    override fun observables() {
        viewModel.userLogged().observe(this, androidx.lifecycle.Observer { isLogged ->
            if (isLogged) {
                toGoRepositoryScreen()
            }else{
                toGoLogginScreen()
            }
        })
    }

    private fun toGoRepositoryScreen(){
        val intent = Intent(this, RepositoryActivity::class.java)
        startActivity(intent)
        finish()
    }

    private fun toGoLogginScreen(){
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }
}