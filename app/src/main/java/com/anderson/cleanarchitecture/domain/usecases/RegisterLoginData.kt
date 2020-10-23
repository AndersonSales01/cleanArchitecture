package com.anderson.cleanarchitecture.domain.usecases

import com.anderson.cleanarchitecture.domain.entities.Login
import com.anderson.cleanarchitecture.domain.repo.IRepoLogin


class RegisterLoginData (private var loginRepository: IRepoLogin) {

    suspend fun execute(login: Login) {
        return loginRepository.registerLogin(login)
    }
}