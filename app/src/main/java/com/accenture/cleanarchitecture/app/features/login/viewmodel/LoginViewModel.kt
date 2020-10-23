package com.accenture.cleanarchitecture.app.features.login.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.accenture.cleanarchitecture.constants.Constants
import com.accenture.cleanarchitecture.domain.entities.Login
import com.accenture.cleanarchitecture.domain.usecases.VerifyCredentialExists
import com.accenture.cleanarchitecture.domain.usecases.VerifyIfUserExists
import com.accenture.cleanarchitecture.util.SharedPreferenceUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginViewModel @Inject constructor (var sharedPref: SharedPreferenceUtil) : ViewModel() {
    //LiveData
    private val _userEmailError: MutableLiveData<String> = MutableLiveData()
    private val _userPasswordError: MutableLiveData<String> = MutableLiveData()
    private val _formLoginValid: MutableLiveData<Boolean> = MutableLiveData()
    private val _isLogged: MutableLiveData<Boolean> = MutableLiveData()
    private val _logingMesageError: MutableLiveData<String> = MutableLiveData()

    private var isEmailValid: Boolean = false
    private var isPasswordValid: Boolean = false
    //UserCases
    @Inject
    lateinit var verifyCredentialExists: VerifyCredentialExists

    fun userEmailData(email: String){
        if(emailValid(email)){
            isEmailValid = true
            _userEmailError.value = ""
        }else {
            isEmailValid = false
            _userEmailError.value = "Formato de e-mail incorreto!"
        }
        formLoginValid()
    }

    fun userPasswordData(password: String) {
        if (userPasswordValid(password)) {
            _userPasswordError.value = ""
            isPasswordValid = true
        } else {
            _userPasswordError.value = "O nome deve ter mais de 5 caracteres!"
            isPasswordValid = false
        }
        formLoginValid()
    }

    private fun formLoginValid(){
        _formLoginValid.value =  isEmailValid && isPasswordValid
    }

    fun callSigIn(login: Login){

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (verifyIfUserExists(login)) {
                    Log.d(Constants.TAG_LOGIN, "Usuario não cadastrado $login")
                    sharedPref.saveStatusLogged(true)
                    _isLogged.postValue(true)
                }else {
                    _isLogged.postValue(false)
                    _logingMesageError.postValue("Usuário não cadastrado!!")
                }
            }
        }
    }



    private suspend fun verifyIfUserExists(login: Login) = verifyCredentialExists.execute(login)

    private fun emailValid(email: String) : Boolean{
        return if (email.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(email).matches()
        } else {
            email.isNotBlank()
        }
    }
    private fun userPasswordValid(password: String): Boolean {
        if (password.length > 5) {
            return true
        }
        return false
    }

    fun isValidForm(): LiveData<Boolean> = _formLoginValid
    fun emailError(): LiveData<String> = _userEmailError
    fun passwordError(): LiveData<String> = _userPasswordError
    fun logingError(): LiveData<String> = _logingMesageError
    fun isLogged():  LiveData<Boolean> = _isLogged
}