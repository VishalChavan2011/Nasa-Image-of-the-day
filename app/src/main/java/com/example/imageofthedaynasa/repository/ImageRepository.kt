package com.example.imageofthedaynasa.repository

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.imageofthedaynasa.Constants
import com.example.imageofthedaynasa.api.ImageService
import com.example.imageofthedaynasa.cache.ImageDatabase
import com.example.imageofthedaynasa.models.ImageInformation
import dagger.hilt.android.qualifiers.ApplicationContext
import org.json.JSONObject
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

/**
 * Repository to fetch the Image of the day information.
 * @property imageService instance of [ImageService] to fetch data from server.
 * @property imageDatabase instance of [ImageDatabase] to fetch data from local cache.
 * @property context  instance of [ApplicationContext]
 */
class ImageRepository @Inject constructor(
    private val imageService: ImageService,
    private val imageDatabase: ImageDatabase,
    @ApplicationContext val context: Context
) {

    private val imageLiveData = MutableLiveData<Response<ImageInformation>>()
    val imageInfo: LiveData<Response<ImageInformation>> = imageLiveData

    /**
     * Get image of the day information from the local cache([ImageDatabase]) or from server.
     * Check if the data is present for the current date in the local cache , if present
     * post the data in [imageLiveData].
     * Else fetch data from the server.
     */
    suspend fun getImageOfTheDay() {
        imageLiveData.postValue(Response.Loading())
        val date = SimpleDateFormat("yyyy-MM-dd", Locale.US).format(Date())
        val imageInformation = imageDatabase.getImageDao().getImageOfTheDay(date = date)
        if (imageInformation == null) {
            fetchDataFromNetwork()
        } else {
            imageLiveData.postValue(Response.Success(imageInformation))
        }
    }

    /**
     * Fetch the data from the server and store it in database for future reference.
     * Check if the internet connection is available, if not then update error in [imageLiveData].
     * Else fetch the data from server and update [imageLiveData] as per response.
     */
    private suspend fun fetchDataFromNetwork() {
        if (!isInternetConnected(context = context)) {
            imageLiveData.postValue(Response.Error(Constants.NO_INTERNET))
        } else {
            try {
                val result = imageService.getImageOfTheDay()
                if (result.isSuccessful && result.body() != null) {
                    imageDatabase.getImageDao().addImageOfTheDay(result.body()!!)
                    imageLiveData.postValue(Response.Success(result.body()))

                } else if (result.errorBody() != null) {
                    val errorObject = JSONObject(result.errorBody()!!.charStream().readText())
                    imageLiveData.postValue(Response.Error(errorObject.getString("message")))

                }
            } catch (e: Exception) {
                imageLiveData.postValue(Response.Error(errorMessage = e.message.toString()))
            }
        }
    }

    /**
     * Check if the internet connection is available or not.
     * @return true or false depending upon the internet connection.
     */
    private fun isInternetConnected(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetwork != null && cm.getNetworkCapabilities(cm.activeNetwork) != null
    }
}