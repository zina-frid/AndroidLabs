package com.zinafrid.download_coroitines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    lateinit var btn : Button
    val viewModel = MainViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById(R.id.downloader)
        btn.setOnClickListener {
            viewModel.downloadImage(MY_URL)
        }
        imageView = findViewById(R.id.imageView)

        viewModel.mutableLiveData.observe(this) {
             imageView.setImageBitmap(it)
        }
    }

    companion object {
        private const val MY_URL = "https://www.meme-arsenal.com/memes/ecdaf55fffca12b0feaca5b3431acdff.jpg"
    }
}