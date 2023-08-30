package com.example.imageofthedaynasa

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.imageofthedaynasa.repository.ImageRepository

/**
 * Factory to provide the instance of [ImageViewModel].
 * @property repository instance of the [ImageRepository] to fetch data
 */
class ImageViewModelFactory(private val repository: ImageRepository): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ImageViewModel(repository) as T
    }
}