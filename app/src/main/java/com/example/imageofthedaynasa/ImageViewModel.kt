package com.example.imageofthedaynasa

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.imageofthedaynasa.models.ImageInformation
import com.example.imageofthedaynasa.repository.ImageRepository
import com.example.imageofthedaynasa.repository.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeout
import javax.inject.Inject

/**
 * ViewModel to hold [ImageInformation] data as per the response.
 * @property imageRepository [ImageRepository] to fetch image information
 */
@HiltViewModel
class ImageViewModel @Inject constructor(private val imageRepository: ImageRepository) : ViewModel() {

    val imageLiveData: LiveData<Response<ImageInformation>> = imageRepository.imageInfo
    init {
        fetchData()
    }

    /**
     * Fetch the image of the day information from the local cache or server.
     */
    internal fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            imageRepository.getImageOfTheDay()
        }
    }

}