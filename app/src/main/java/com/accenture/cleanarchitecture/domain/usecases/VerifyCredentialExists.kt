package com.accenture.cleanarchitecture.domain.usecases

import android.util.Log
import com.accenture.cleanarchitecture.constants.Constants
import com.accenture.cleanarchitecture.domain.entities.Login
import com.accenture.cleanarchitecture.domain.repo.IRepoLogin

class VerifyCredentialExists (private var loginRepository: IRepoLogin){
    suspend fun execute(login: Login): Boolean {
        Log.d(Constants.TAG_LOGIN, "execute $login")
        val user = loginRepository.getCredetialLogin(login)
        if (user != null) {
            return true
        }
        return false
    }
}