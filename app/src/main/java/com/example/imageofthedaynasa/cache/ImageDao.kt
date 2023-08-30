package com.example.imageofthedaynasa.cache

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.imageofthedaynasa.models.ImageInformation

/**
 * Interface to perform the data access operation such as insertion of [ImageInformation] and
 * retrieving image for the given date.
 */
@Dao
interface ImageDao {

    /**
     * Retrieve [ImageInformation] for the given date from database.
     * @param date for which the information should be retrieve.
     * @return [ImageInformation] is the data is present for the given date else null.
     */
    @Query("SELECT * FROM Image WHERE date = :date")
    suspend fun getImageOfTheDay(date: String): ImageInformation?

    /**
     * Insert the image of the day information in the database.
     * @param imageInformation information of the image to be inserted into database.
     */
    @Insert
    suspend fun addImageOfTheDay(imageInformation: ImageInformation)
}