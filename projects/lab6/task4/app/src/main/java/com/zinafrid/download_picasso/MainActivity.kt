package com.zinafrid.download_picasso

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.squareup.picasso.Picasso


class MainActivity : AppCompatActivity() {

    private lateinit var imageView: ImageView
    lateinit var btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        imageView = findViewById(R.id.imageView)
        btn = findViewById(R.id.downloader)
        btn.setOnClickListener {
            Picasso.get().load(MY_URL).into(imageView)
        }
    }

    companion object {
        private const val MY_URL = "https://www.meme-arsenal.com/memes/ecdaf55fffca12b0feaca5b3431acdff.jpg"
    }
}