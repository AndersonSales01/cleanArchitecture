package com.accenture.cleanarchitecture.data.api

import com.accenture.cleanarchitecture.data.enuns.Status

data class Resource<out T>(val status: Status, val data: T?, val message: String?, val statusCode: Int?) {
    companion object {
        fun <T> success(data: T, statusCode : Int): Resource<T> = Resource(status = Status.SUCCESS, data = data, message = null,statusCode =statusCode)

        fun <T> error(data: T?, message: String, statusCode : Int): Resource<T> =
            Resource(status = Status.ERROR, data = data, message = message , statusCode = statusCode)

        fun <T> result(status: Status, data: T, statusCode : Int, message : String): Resource<T> = Resource(status = status, data = data, message = message,statusCode =statusCode)

    }
}