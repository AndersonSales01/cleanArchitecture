package com.accenture.cleanarchitecture.app.features.registeruser.viewmodel


import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.accenture.cleanarchitecture.constants.Constants
import com.accenture.cleanarchitecture.domain.entities.Login
import com.accenture.cleanarchitecture.domain.usecases.RegisterLoginData
import com.accenture.cleanarchitecture.domain.usecases.VerifyIfUserExists
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterLoginViewModel @Inject constructor() : ViewModel() {
    //LiveData
    private val _userName = MutableLiveData<String>()
    private val _userConfirmPasswordError: MutableLiveData<String> = MutableLiveData()
    private val _formRegisterValid: MutableLiveData<Boolean> = MutableLiveData()
    private val _userNameMesageError: MutableLiveData<String> = MutableLiveData()
    private val _userPasswordError: MutableLiveData<String> = MutableLiveData()
    private val _loginExistingMesage: MutableLiveData<String> = MutableLiveData()
    private val _userEmailError: MutableLiveData<String> = MutableLiveData()
    private val _registerUserSucess: MutableLiveData<Boolean> = MutableLiveData()

    private var isUserNameValid: Boolean = false
    private var isEmailValid: Boolean = false
    private var isPasswordValid: Boolean = false
    private var isConfirmPasswordValid: Boolean = false

    //UseCases
    @Inject
    lateinit var registerLogin: RegisterLoginData
    @Inject
    lateinit var verifyIfUserExists: VerifyIfUserExists


    fun userNameData(userName: String) {
        if (userNameValid(userName)) {
            _userName.value = userName
            isUserNameValid = true
            _userNameMesageError.value = ""
        } else {
            _userNameMesageError.value = "O nome deve ter mais de 3 caracteres!"
            isUserNameValid = false
        }

        formRegisterValidate()
    }

    fun userEmailData(email: String){
        if(emailValid(email)){
            isEmailValid = true
            _userEmailError.value = ""
        }else {
            isEmailValid = false
            _userEmailError.value = "Formato de e-mail incorreto!"
        }
    }

    fun userPasswordData(password: String) {
        if (userPasswordValid(password)) {
            _userPasswordError.value = ""
            isPasswordValid = true
        } else {
            _userPasswordError.value = "O nome deve ter mais de 5 caracteres!"
            isPasswordValid = false
        }

        formRegisterValidate()
    }

    fun userConfirmPasswordData(password: String, confirmPassword: String) {
        if (userConfirmPasswordValid(password,confirmPassword)) {
            _userConfirmPasswordError.value = ""
            isConfirmPasswordValid = true
        } else {
            _userConfirmPasswordError.value = "Por favor confirmar a senha correta!! "
            isConfirmPasswordValid = false
        }

        formRegisterValidate()
    }

    private fun userNameValid(userName: String): Boolean {
        if (userName.length > 3) {
            return true
        }
        return false
    }

    private fun userPasswordValid(password: String): Boolean {
        if (password.length > 5) {
            return true
        }
        return false
    }

    private fun emailValid(email: String) : Boolean{
       return if (email.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
            email.isNotBlank()
        }
    }

    private fun userConfirmPasswordValid(password: String,confirmPassword: String): Boolean {
        Log.d("Form","senha: $password")
        Log.d("Form","confirmar senha: $confirmPassword")
        return password == confirmPassword
    }

    private fun formRegisterValidate() {
        _formRegisterValid.value =
            isUserNameValid && isEmailValid && isPasswordValid && isConfirmPasswordValid
    }

    fun RegisterLoginData(login: Login) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (!verifyUserExisting(login)) {
                    Log.d(Constants.TAG_LOGIN, "Usuario não cadastrado $login")

                    registerLogin.execute(login)
                    _registerUserSucess.postValue(true)
                }else {
                    _loginExistingMesage.postValue("Usuário já cadastrado!!")
                }
            }
        }
    }

    private suspend fun verifyUserExisting(login: Login) = verifyIfUserExists.execute(login)

    fun userNameLiveData(): LiveData<String> = _userName
    fun userNameError(): LiveData<String> = _userNameMesageError
    fun passwordError(): LiveData<String> = _userPasswordError
    fun confirmPasswordError(): LiveData<String> = _userConfirmPasswordError
    fun emailError(): LiveData<String> = _userEmailError
    fun isValidForm(): LiveData<Boolean> = _formRegisterValid
    fun userExistingMesage(): LiveData<String> = _loginExistingMesage
    fun registerUserSucess(): LiveData<Boolean> = _registerUserSucess
}