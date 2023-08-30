package com.example.imageofthedaynasa.repository

/**
 * Class to hold response of the get image data request.
 * @property imageInformation holds the image information return for the request during success scenario
 * @property errorMessage holds the error message occurred during the error scenario
 */
sealed class Response<T>(
    val imageInformation: T? = null,
    val errorMessage: String? = null
) {
    class Loading<T>() : Response<T>()
    class Success<T>(data: T? = null) : Response<T>(imageInformation = data)
    class Error<T>(errorMessage: String) : Response<T>(errorMessage = errorMessage)
}
