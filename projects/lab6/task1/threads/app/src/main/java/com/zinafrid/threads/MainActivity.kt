package com.zinafrid.threads

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    var secondsElapsed: Int = 0
    lateinit var textSecondsElapsed: TextView
    private lateinit var sharedPref: SharedPreferences
    private lateinit var backgroundThread: Thread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textSecondsElapsed = findViewById(R.id.timer)
        sharedPref = getSharedPreferences(SECONDS, Context.MODE_PRIVATE)
    }

    override fun onStart() {
        secondsElapsed = sharedPref.getInt(SECONDS, 0)
        Log.d("MainActivity", "OnStart: seconds = $secondsElapsed")
        backgroundThread = Thread {
            try {
                while (!Thread.currentThread().isInterrupted) {
                    Log.d("MainActivity", "${Thread.currentThread()} is iterating")
                    Thread.sleep(1000)
                    textSecondsElapsed.post {
                        textSecondsElapsed.text = "${secondsElapsed++}"
                    }
                }
            } catch (e: InterruptedException) {
                Thread.currentThread().interrupt()
            }
        }
        backgroundThread.start()
        super.onStart()
    }

    override fun onStop() {
        val editor = sharedPref.edit()
        editor.putInt(SECONDS, secondsElapsed)
        editor.apply()
        Log.d("MainActivity", "OnStop: seconds = $secondsElapsed")
        backgroundThread.interrupt()
        super.onStop()
    }

    companion object {
        const val SECONDS = "Seconds"
    }
}
