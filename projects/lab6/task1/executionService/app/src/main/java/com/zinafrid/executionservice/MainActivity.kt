package com.zinafrid.executionservice

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    private lateinit var sharedPref: SharedPreferences
    private lateinit var executor: ExecutorService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.timer)
        sharedPref = getSharedPreferences(SECONDS, Context.MODE_PRIVATE)
    }

    override fun onStart() {
        secondsElapsed = sharedPref.getInt(SECONDS, 0)
        Log.d("MainActivity", "OnStart: seconds = $secondsElapsed")
        executor = Executors.newFixedThreadPool(1)
        executor.execute {
                while (!executor.isShutdown) {
                    Log.d("MainActivity", "${Thread.currentThread()} is iterating")
                    Thread.sleep(1000)
                    textSecondsElapsed.post {
                        textSecondsElapsed.text = "${secondsElapsed++}"
                    }
                }
        }
        super.onStart()
    }

    override fun onStop() {
        val editor = sharedPref.edit()
        editor.putInt(SECONDS, secondsElapsed)
        editor.apply()
        Log.d("MainActivity", "OnStop: seconds = $secondsElapsed")
        executor.shutdown()
        super.onStop()
    }

    companion object { const val SECONDS = "Seconds" }
}
