package com.anderson.cleanarchitecture.data.mappers

import com.anderson.cleanarchitecture.data.database.LoginPO
import com.anderson.cleanarchitecture.domain.entities.Login

object LoginPOMapper {
    fun toLoginPO(login: Login): LoginPO {
        return LoginPO(login.name,login.email,login.password,0)
    }
}