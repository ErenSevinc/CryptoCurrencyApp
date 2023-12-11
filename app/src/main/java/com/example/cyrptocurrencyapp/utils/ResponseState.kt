package com.example.cyrptocurrencyapp.utils

sealed class ResponseState<T>(val data :T ?= null, val errorMessage: String ?= null) {
    class Loading<T>: ResponseState<T>()
    class Success<T>(data: T?): ResponseState<T>(data)
    class Error<T>(errorMessage: String?): ResponseState<T>(errorMessage = errorMessage)
}
