package com.anderson.cleanarchitecture.data.repository

import android.util.Log
import com.anderson.cleanarchitecture.constants.Constants.TAG_LOGIN
import com.anderson.cleanarchitecture.data.database.LoginDao
import com.anderson.cleanarchitecture.data.mappers.LoginMapper
import com.anderson.cleanarchitecture.data.mappers.LoginPOMapper
import com.anderson.cleanarchitecture.domain.entities.Login
import com.anderson.cleanarchitecture.domain.repo.IRepoLogin
import javax.inject.Inject

class RepoLoginImpl @Inject constructor(var loginDao: LoginDao) : IRepoLogin {

    override suspend fun registerLogin(login: Login) {
        val loginPo = LoginPOMapper.toLoginPO(login)

        loginDao.insert(loginPo)

        Log.d(TAG_LOGIN, "registerLogin " + loginDao.getUserEmail(loginPo.email))
    }

    override suspend fun getUser(login: Login): Login? {
        var loginObject: Login? = null
        Log.d(TAG_LOGIN, "getLoginPO $login")
        val loginPo = loginDao.getUserEmail(LoginPOMapper.toLoginPO(login!!).email)

        if (loginPo != null) {
            loginObject = LoginMapper.toLogin(loginPo)
        }
        return loginObject
    }

    override suspend fun getCredetialLogin(login: Login): Login? {
        var loginObject: Login? = null
        Log.d(TAG_LOGIN, "getLoginPO $login")

       val loginPOConverted = LoginPOMapper.toLoginPO(login!!)
        val loginPo = loginDao.getCreditial(loginPOConverted.email, loginPOConverted.password)

        if (loginPo != null) {
            loginObject = LoginMapper.toLogin(loginPo)
        }
        return loginObject
    }
}