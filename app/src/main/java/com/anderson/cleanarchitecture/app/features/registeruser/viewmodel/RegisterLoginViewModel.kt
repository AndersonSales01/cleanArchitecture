package com.anderson.cleanarchitecture.app.features.registeruser.viewmodel


import android.content.Context
import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anderson.cleanarchitecture.constants.Constants
import com.anderson.cleanarchitecture.domain.entities.Login
import com.anderson.cleanarchitecture.domain.usecases.RegisterLoginData
import com.anderson.cleanarchitecture.domain.usecases.VerifyIfUserExists
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class RegisterLoginViewModel @Inject constructor(context: Context) : ViewModel() {
    //LiveData
    private val userName = MutableLiveData<String>()
    private val userConfirmPasswordError: MutableLiveData<String> = MutableLiveData()
    private val formRegisterValid: MutableLiveData<Boolean> = MutableLiveData()
    private val userNameMesageError: MutableLiveData<String> = MutableLiveData()
    private val userPasswordError: MutableLiveData<String> = MutableLiveData()
    private val loginExistingMesage: MutableLiveData<String> = MutableLiveData()
    private val userEmailError: MutableLiveData<String> = MutableLiveData()
    private val registerUserSucess: MutableLiveData<Boolean> = MutableLiveData()

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
            this.userName.value = userName
            isUserNameValid = true
            userNameMesageError.value = ""
        } else {
            userNameMesageError.value = "O nome deve ter mais de 3 caracteres!"
            isUserNameValid = false
        }

        formRegisterValidate()
    }

    fun userEmailData(email: String){
        if(emailValid(email)){
            isEmailValid = true
            userEmailError.value = ""
        }else {
            isEmailValid = false
            userEmailError.value = "Formato de e-mail incorreto!"
        }
    }

    fun userPasswordData(password: String) {
        if (userPasswordValid(password)) {
            userPasswordError.value = ""
            isPasswordValid = true
        } else {
            userPasswordError.value = "O nome deve ter mais de 5 caracteres!"
            isPasswordValid = false
        }

        formRegisterValidate()
    }

    fun userConfirmPasswordData(password: String, confirmPassword: String) {
        if (userConfirmPasswordValid(password,confirmPassword)) {
            userConfirmPasswordError.value = ""
            isConfirmPasswordValid = true
        } else {
            userConfirmPasswordError.value = "Por favor confirmar a senha correta!! "
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
        formRegisterValid.value =
            isUserNameValid && isEmailValid && isPasswordValid && isConfirmPasswordValid
    }

    fun RegisterLoginData(login: Login) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                if (!verifyUserExisting(login)) {
                    Log.d(Constants.TAG_LOGIN, "Usuario não cadastrado $login")

                    registerLogin.execute(login)
                    registerUserSucess.postValue(true)
                }else {
                    loginExistingMesage.postValue("Usuário já cadastrado!!")
                }
            }
        }
    }

    private suspend fun verifyUserExisting(login: Login) = verifyIfUserExists.execute(login)

    fun userNameLiveData(): LiveData<String> = userName
    fun userNameError(): LiveData<String> = userNameMesageError
    fun passwordError(): LiveData<String> = userPasswordError
    fun confirmPasswordError(): LiveData<String> = userConfirmPasswordError
    fun emailError(): LiveData<String> = userEmailError
    fun isValidForm(): LiveData<Boolean> = formRegisterValid
    fun userExistingMesage(): LiveData<String> = loginExistingMesage
    fun registerUserSucess(): LiveData<Boolean> = registerUserSucess
}