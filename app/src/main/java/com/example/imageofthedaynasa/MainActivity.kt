package com.example.imageofthedaynasa

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.imageofthedaynasa.databinding.ActivityMainBinding
import com.example.imageofthedaynasa.models.ImageInformation
import com.example.imageofthedaynasa.repository.ImageRepository
import com.example.imageofthedaynasa.repository.Response
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var imageRepository: ImageRepository

    private lateinit var viewModel: ImageViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar?.title = Constants.APP_NAME

        initializeViewModel()
    }

    private fun initializeViewModel() {
        viewModel = ViewModelProvider(
            this,
            ImageViewModelFactory(imageRepository)
        )[ImageViewModel::class.java]

        viewModel.imageLiveData.observe(this, Observer {
            when (it) {
                is Response.Loading -> binding.progressbar.visibility = View.VISIBLE
                is Response.Success -> setSuccessResponse(it.imageInformation)
                is Response.Error -> setErrorResponse(it.errorMessage)
            }
        })
    }

    private fun setErrorResponse(errorMessage: String?) {
        hideProgressBar()
        with(binding.errorScreen) {
            errorTextDetails.text = errorMessage
            root.visibility = View.VISIBLE
            retry.setOnClickListener {
                viewModel.fetchData()
                root.visibility = View.GONE
            }
        }
    }

    private fun setSuccessResponse(imageInformation: ImageInformation?) {
       hideProgressBar()
        imageInformation?.let { information ->
            with(binding.imageScreen) {
                Glide.with(this@MainActivity)
                    .load(information.url)
                    .placeholder(R.drawable.placeholder_image)
                    .into(imageviewNasaImage)
                textviewTitle.text = information.title
                textviewDate.text = information.date
                textviewDescriptionDetails.text = information.explanation
            }
        }
        binding.imageScreen.root.visibility = View.VISIBLE
    }

    private fun hideProgressBar() {
        binding.progressbar.visibility = View.GONE
    }
}