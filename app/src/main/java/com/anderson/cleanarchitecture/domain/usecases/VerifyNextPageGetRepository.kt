package com.anderson.cleanarchitecture.domain.usecases

import android.util.Log
import com.anderson.cleanarchitecture.constants.Constants

class VerifyNextPageGetRepository {
    fun execute(currentItems: Int, scrollOutItems: Int, totalItems: Int): Boolean {
        Log.d(Constants.TAG_REPOSITORY, "currentItems: ${currentItems}")
        if (currentItems + scrollOutItems >= totalItems) {
            return true
        }
        return false
    }
}