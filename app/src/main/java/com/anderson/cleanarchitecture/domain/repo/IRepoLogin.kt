package com.anderson.cleanarchitecture.domain.repo

import com.anderson.cleanarchitecture.domain.entities.Login

interface IRepoLogin {
   suspend fun registerLogin(login: Login)
   suspend fun getUser(login: Login): Login?
   suspend fun getCredetialLogin(login: Login): Login?
}