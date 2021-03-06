package com.zinafrid.coroutines

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.timer)
        sharedPref = getSharedPreferences(SECONDS, Context.MODE_PRIVATE)

        val job = lifecycleScope.launchWhenStarted {
            Log.d("MainActivity", "Coroutine is launched")
            while (isActive) {
                Log.d("MainActivity", "Coroutine is working")
                delay(1000)
                textSecondsElapsed.text = "${secondsElapsed++}"

            }

        }

        job.invokeOnCompletion {
            Log.d("MainActivity", "Coroutine is completed")
        }
    }

    override fun onStart() {
        secondsElapsed = sharedPref.getInt(SECONDS, 0)
        Log.d("MainActivity", "OnStart: seconds = $secondsElapsed")
        super.onStart()
    }

    override fun onStop() {
        val editor = sharedPref.edit()
        editor.putInt(SECONDS, secondsElapsed)
        editor.apply()
        Log.d("MainActivity", "OnStop: seconds = $secondsElapsed")
        super.onStop()
    }

    companion object {
        const val SECONDS = "Seconds"
    }
}
