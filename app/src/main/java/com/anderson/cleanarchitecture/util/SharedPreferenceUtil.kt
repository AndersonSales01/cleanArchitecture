package com.anderson.cleanarchitecture.util

import android.content.Context
import android.content.SharedPreferences
import com.anderson.cleanarchitecture.R

class SharedPreferenceUtil(context: Context){
    private var prefs: SharedPreferences = context.getSharedPreferences(context.getString(R.string.app_name), Context.MODE_PRIVATE)

    companion object {
        const val LOGEED = "logged"
    }

    fun saveStatusLogged(logged: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(LOGEED, logged)
        editor.apply()
    }

    fun getStatusLogged() : Boolean? {
        return prefs.getBoolean(LOGEED, false)
    }
}