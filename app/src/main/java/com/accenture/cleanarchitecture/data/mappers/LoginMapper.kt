package com.accenture.cleanarchitecture.data.mappers

import com.accenture.cleanarchitecture.data.database.LoginPO
import com.accenture.cleanarchitecture.domain.entities.Login

object LoginMapper {
    fun toLogin(loginPo: LoginPO): Login {
        return Login(loginPo.name,loginPo.email,loginPo.password)
    }
}