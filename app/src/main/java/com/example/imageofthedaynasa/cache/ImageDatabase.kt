package com.example.imageofthedaynasa.cache

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.imageofthedaynasa.models.ImageInformation

/**
 * Database to store data locally using [RoomDatabase]
 */
@Database(entities = [ImageInformation::class], version = 1)
abstract class ImageDatabase : RoomDatabase() {

    /**
     * Method to create instance of [ImageDao] which will be used for Data operations
     * @return instance of [ImageDao]
     */
    abstract fun getImageDao(): ImageDao
}