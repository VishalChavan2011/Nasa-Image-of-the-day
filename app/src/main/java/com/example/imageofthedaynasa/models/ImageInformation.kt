package com.example.imageofthedaynasa.models

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Data class to hold the information of the image.
 * @property imageId primary key for the database.
 * @property date date of the image.
 * @property explanation explanation about the image.
 * @property media_type type of Media of the image.
 * @property service_version service version of the image.
 * @property title title of the image.
 * @property url image url to fetch the image
 */
@Entity(tableName = "Image")
data class ImageInformation(
    @PrimaryKey(autoGenerate = true)
    val imageId: Int,
    val date: String,
    val explanation: String,
    val media_type: String,
    val service_version: String,
    val title: String,
    val url: String
)