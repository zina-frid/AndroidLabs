package com.zinafrid.lifecycle

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("MainActivity", "onCreate()")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        Log.d("MainActivity", "onStart()")
        super.onStart()
    }

    override fun onResume() {
        Log.d("MainActivity", "onResume()")
        super.onResume()
    }

    override fun onPause() {
        Log.d("MainActivity", "onPause()")
        super.onPause()
    }

    override fun onStop() {
        Log.d("MainActivity", "onStop()")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d("MainActivity", "onDestroy()")
        super.onDestroy()
    }
}