package com.accenture.cleanarchitecture.app.features.main.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.accenture.cleanarchitecture.util.SharedPreferenceUtil
import javax.inject.Inject

class MainViewModel @Inject constructor(var sharedPref: SharedPreferenceUtil) : ViewModel() {

    private val _userLogged: MutableLiveData<Boolean> = MutableLiveData()

    fun verifyUserLogged() {
        val isLogged = sharedPref.getStatusLogged()
        if (isLogged != null && isLogged) {
            _userLogged.postValue(true)
        }else{
            _userLogged.postValue(false)
        }
    }

    fun userLogged(): LiveData<Boolean> = _userLogged
}