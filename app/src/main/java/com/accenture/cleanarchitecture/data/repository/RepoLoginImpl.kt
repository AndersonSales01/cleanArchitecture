package com.accenture.cleanarchitecture.data.repository

import android.util.Log
import com.accenture.cleanarchitecture.constants.Constants.TAG_LOGIN
import com.accenture.cleanarchitecture.data.database.LoginDao
import com.accenture.cleanarchitecture.data.mappers.LoginMapper
import com.accenture.cleanarchitecture.data.mappers.LoginPOMapper
import com.accenture.cleanarchitecture.domain.entities.Login
import com.accenture.cleanarchitecture.domain.repo.IRepoLogin
import com.accenture.cleanarchitecture.util.SharedPreferenceUtil
import javax.inject.Inject
import kotlin.math.log

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