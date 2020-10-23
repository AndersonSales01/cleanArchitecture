package com.accenture.cleanarchitecture.app.features.registeruser.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.accenture.cleanarchitecture.R
import com.accenture.cleanarchitecture.app.common.BaseActivity
import com.accenture.cleanarchitecture.app.features.registeruser.viewmodel.RegisterLoginViewModel
import com.accenture.cleanarchitecture.domain.entities.Login
import kotlinx.android.synthetic.main.activity_register_login.*
import java.util.*
import javax.inject.Inject

class RegisterLoginActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val viewModel: RegisterLoginViewModel by lazy {
        ViewModelProviders.of(this, viewModelFactory)[RegisterLoginViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register_login)

        initialize()
    }

    override fun initialize() {
        subComponent.inject(this)

        initViews()

        observables()
    }

    override fun initViews() {
        register_user_date_birth.setOnClickListener {
            birthDatePickerDialog()
        }

        btn_register_user.setOnClickListener {
            Toast.makeText(this, "Clicou", Toast.LENGTH_LONG).show()
        }

        register_user_name.afterTextChanged {
            viewModel.userNameData(it)
        }

        register_user_password.apply {
            afterTextChanged {
                viewModel.userPasswordData(it)
            }
        }

        register_user_confirm_password.apply {
            afterTextChanged {
                viewModel.userConfirmPasswordData(register_user_password.text.toString(), it)
            }
        }

        register_user_email.afterTextChanged {
            viewModel.userEmailData(it)
        }

        btn_register_user.setOnClickListener {
            viewModel.RegisterLoginData(
                Login(
                    register_user_name.text.toString(),
                    register_user_email.text.toString(),
                    register_user_password.text.toString()
                )
            )
        }
    }

    override fun observables() {
        viewModel.userNameError().observe(this, androidx.lifecycle.Observer { errorMesage ->
            if (errorMesage != "") {
                register_user_name.error = errorMesage
            }
        })

        viewModel.passwordError().observe(this, androidx.lifecycle.Observer { errorMesage ->
            if (errorMesage != "") {
                register_user_password.error = errorMesage
            }
        })

        viewModel.isValidForm().observe(this, androidx.lifecycle.Observer { valid ->
            btn_register_user.isEnabled = valid
        })

        viewModel.confirmPasswordError().observe(this, androidx.lifecycle.Observer { errorMesage ->
            if (errorMesage != "") {
                register_user_confirm_password.error = errorMesage
            }
        })

        viewModel.emailError().observe(this, androidx.lifecycle.Observer { errorMesage ->
            if (errorMesage != "") {
                register_user_email.error = errorMesage
            }
        })

        viewModel.userExistingMesage().observe(this, androidx.lifecycle.Observer { mesage ->
            if (mesage != "") {
                Toast.makeText(this, mesage, Toast.LENGTH_LONG).show()
            }
        })

        viewModel.registerUserSucess().observe(this, androidx.lifecycle.Observer { isSucess ->
            if (isSucess) {
                finish()
            }
        })
    }

    private fun birthDatePickerDialog() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val day = c.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(
            this,
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                // Display Selected date in TextView
                register_user_date_birth.text = "$dayOfMonth/$month/$year"
            },
            year,
            month,
            day
        )

        dpd.show()
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
