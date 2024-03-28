package com.example.domain.models

sealed class Resource<out T: Any> {
    data object Loading: Resource<Nothing>()
    data class Success<out T: Any> (val data: T): Resource<T>()
    data class Error (val errorCode: Int): Resource<Nothing>()

    companion object {
        const val NO_INTERNET = 503
    }
}