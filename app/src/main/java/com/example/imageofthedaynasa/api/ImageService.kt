package com.example.imageofthedaynasa.api

import com.example.imageofthedaynasa.Constants.Companion.KEY
import com.example.imageofthedaynasa.models.ImageInformation
import retrofit2.Response
import retrofit2.http.GET

/**
 * Interface to get the Image of the day information from the server.
 */
interface ImageService {
    /**
     * Suspending function to get the image of the day information from the server using Retrofit.
     * @return [Response] depending upon the success and failure scenario.
     */
    @GET("apod?api_key=${KEY}")
    suspend fun getImageOfTheDay(): Response<ImageInformation>
}