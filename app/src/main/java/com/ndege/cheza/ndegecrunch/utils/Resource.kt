package com.ndege.cheza.ndegecrunch.utils

sealed class Resource<T>(
    val data: T? = null, val errorMessage: String? = null, val isLoading: Boolean = false
) {
    class Success<T>(data: T?) :
        Resource<T>(data)
    class Error<T>(errorMessage: String?, data: T? = null) :
        Resource<T>(data, errorMessage)
    class Loading<T>(isLoading: Boolean, data: T? = null, errorMessage: String? = "") :
        Resource<T>(data, errorMessage, isLoading)
}