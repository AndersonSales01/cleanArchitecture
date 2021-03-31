package com.anderson.cleanarchitecture.app.features.login.viewmodel

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anderson.cleanarchitecture.constants.Constants
import com.anderson.cleanarchitecture.domain.entities.Login
import com.anderson.cleanarchitecture.domain.usecases.VerifyCredentialExists
import com.anderson.cleanarchitecture.util.SharedPreferenceUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class LoginViewModel @Inject constructor (var sharedPref: SharedPreferenceUtil) : ViewModel() {
    //LiveData
    private val userEmailError: MutableLiveData<String> = MutableLiveData()
    private val userPasswordError: MutableLiveData<String> = MutableLiveData()
    private val formLoginValid: MutableLiveData<Boolean> = MutableLiveData()
    private val isLogged: MutableLiveData<Boolean> = MutableLiveData()
    private val logingMesageError: MutableLiveData<String> = MutableLiveData()
    private val loading: MutableLiveData<Boolean> = MutableLiveData()

    private var isEmailValid: Boolean = false
    private var isPasswordValid: Boolean = false
    //UserCases
    @Inject
    lateinit var verifyCredentialExists: VerifyCredentialExists

    fun userEmailData(email: String){
        if(emailValid(email)){
            isEmailValid = true
            userEmailError.value = ""
        }else {
            isEmailValid = false
            userEmailError.value = "Formato de e-mail incorreto!"
        }
        formLoginValid()
    }

    fun userPasswordData(password: String) {
        if (userPasswordValid(password)) {
            userPasswordError.value = ""
            isPasswordValid = true
        } else {
            userPasswordError.value = "O nome deve ter mais de 5 caracteres!"
            isPasswordValid = false
        }
        formLoginValid()
    }

    private fun formLoginValid(){
        formLoginValid.value =  isEmailValid && isPasswordValid
    }

    fun callSigIn(login: Login){
        loading.value = true
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (verifyIfUserExists(login)) {
                    Log.d(Constants.TAG_LOGIN, "Usuario não cadastrado $login")
                    sharedPref.saveStatusLogged(true)
                    isLogged.postValue(true)
                }else {
                    isLogged.postValue(false)
                    logingMesageError.postValue("Usuário não cadastrado!!")
                }
                loading.postValue(false)
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

    fun isValidForm(): LiveData<Boolean> = formLoginValid
    fun emailError(): LiveData<String> = userEmailError
    fun passwordError(): LiveData<String> = userPasswordError
    fun logingError(): LiveData<String> = logingMesageError
    fun isLogged():  LiveData<Boolean> = isLogged
    fun loading():  LiveData<Boolean> = loading
}