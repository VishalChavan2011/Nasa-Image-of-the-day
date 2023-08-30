package com.example.imageofthedaynasa.cache

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Module to create [Singleton] object [ImageDatabase] and [ImageDao]
 */
@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideImageDatabase(context: Application): ImageDatabase {
        return Room.databaseBuilder(context, ImageDatabase::class.java, "ImageDB").build()
    }

    @Singleton
    @Provides
    fun provideDao(db: ImageDatabase) = db.getImageDao()
}