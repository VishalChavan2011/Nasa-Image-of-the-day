package com.example.imageofthedaynasa.api

import com.example.imageofthedaynasa.Constants.Companion.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

/**
 * Module to create the [Singleton] object of the [ImageService].
 */
@InstallIn(SingletonComponent::class)
@Module
object ImageServiceModule {

    @Provides
    @Singleton
    fun providesRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
    }

    @Provides
    @Singleton
    fun providesImageService(retrofit: Retrofit): ImageService {
        return retrofit.create(ImageService::class.java)
    }
}