package com.accenture.cleanarchitecture.domain.usecases

import com.accenture.cleanarchitecture.domain.entities.Login
import com.accenture.cleanarchitecture.domain.repo.IRepoLogin


class RegisterLoginData (private var loginRepository: IRepoLogin) {

    suspend fun execute(login: Login) {
        return loginRepository.registerLogin(login)
    }
}