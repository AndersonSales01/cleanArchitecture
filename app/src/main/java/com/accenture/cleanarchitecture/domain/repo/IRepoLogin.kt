package com.accenture.cleanarchitecture.domain.repo

import com.accenture.cleanarchitecture.domain.entities.Login

interface IRepoLogin {
   suspend fun registerLogin(login: Login)
   suspend fun getUser(login: Login): Login?
   suspend fun getCredetialLogin(login: Login): Login?
}