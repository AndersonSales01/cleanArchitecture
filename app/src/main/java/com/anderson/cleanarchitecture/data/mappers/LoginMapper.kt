package com.anderson.cleanarchitecture.data.mappers

import com.anderson.cleanarchitecture.data.database.LoginPO
import com.anderson.cleanarchitecture.domain.entities.Login

object LoginMapper {
    fun toLogin(loginPo: LoginPO): Login {
        return Login(loginPo.name,loginPo.email,loginPo.password)
    }
}