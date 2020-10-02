package com.accenture.cleanarchitecture.domain.usecases

class VerifyNextPageGetRepository {
    fun execute(currentItems: Int, scrollOutItems: Int, totalItems: Int): Boolean {
        if (currentItems + scrollOutItems >= totalItems) {
            return true
        }
        return false
    }
}