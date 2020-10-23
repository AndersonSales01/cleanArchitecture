package com.accenture.cleanarchitecture.app.features.login.ui

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.accenture.cleanarchitecture.R
import com.accenture.cleanarchitecture.app.common.BaseActivity
import com.accenture.cleanarchitecture.app.features.login.viewmodel.LoginViewModel
import com.accenture.cleanarchitecture.app.features.registeruser.ui.RegisterLoginActivity
import com.accenture.cleanarchitecture.app.features.registeruser.ui.afterTextChanged
import com.accenture.cleanarchitecture.app.features.repository.ui.view.RepositoryActivity
import com.accenture.cleanarchitecture.domain.entities.Login
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_register_login.*
import javax.inject.Inject

class LoginActivity : BaseActivity() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private val viewModel: LoginViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[LoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initialize()
    }

    override fun initialize() {
        subComponent.inject(this)

        initViews()

        observables()


    }

    override fun initViews() {

        button_screen_register.setOnClickListener {
            toGoRegisterUser()
        }

        user_email.afterTextChanged {
            viewModel.userEmailData(it)
        }

        user_password.apply {
            afterTextChanged {
                viewModel.userPasswordData(it)
            }
        }

        btn_sigIn.setOnClickListener {
            viewModel.callSigIn(
                Login(
                   "",
                    user_email.text.toString(),
                    user_password.text.toString()
                ))
        }
    }

    override fun observables() {
        viewModel.isValidForm().observe(this, androidx.lifecycle.Observer { valid ->
            btn_sigIn.isEnabled = valid
        })

        viewModel.passwordError().observe(this, androidx.lifecycle.Observer { errorMesage ->
            if (errorMesage != "") {
                user_password.error = errorMesage
            }
        })

        viewModel.emailError().observe(this, androidx.lifecycle.Observer { errorMesage ->
            if (errorMesage != "") {
                user_email.error = errorMesage
            }
        })

        viewModel.isLogged().observe(this, androidx.lifecycle.Observer { isLogged ->
            if (isLogged) {
                toGoRepositoryScreen()
            }
        })

        viewModel.logingError().observe(this, androidx.lifecycle.Observer { mesageError ->
            if (mesageError != "") {
                Toast.makeText(this, mesageError, Toast.LENGTH_LONG).show()
            }
        })
    }


    private fun toGoRegisterUser(){
        val intent = Intent(this, RegisterLoginActivity::class.java)
        startActivity(intent)
    }

    private fun toGoRepositoryScreen(){
        val intent = Intent(this, RepositoryActivity::class.java)
        startActivity(intent)
        finish()
    }
}
fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }

        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}
        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}