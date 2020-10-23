package com.accenture.cleanarchitecture.data.mappers

import com.accenture.cleanarchitecture.data.database.LoginPO
import com.accenture.cleanarchitecture.data.entities.PullRequestResponse
import com.accenture.cleanarchitecture.domain.entities.Login
import com.accenture.cleanarchitecture.domain.entities.PullRequest
import com.accenture.cleanarchitecture.domain.entities.User

object LoginPOMapper {
    fun toLoginPO(login: Login): LoginPO {
        return LoginPO(login.name,login.email,login.password,0)
    }
}