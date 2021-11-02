package com.zinafrid.task4_1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.zinafrid.task4_1.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var hasBeenClicked = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        binding.button.setOnClickListener { onButtonClicked() }
        setContentView(binding.root)
    }

    private fun onButtonClicked() {
        if (!hasBeenClicked) {
            binding.button.text = getString(R.string.clicked)
            hasBeenClicked = true
        }
    }
}