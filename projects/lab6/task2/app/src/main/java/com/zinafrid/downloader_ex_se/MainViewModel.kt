package com.zinafrid.downloader_ex_se

import android.app.Application
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainViewModel(application: Application) : AndroidViewModel(application) {

    val mutableLiveData = MutableLiveData<Bitmap>()
    private val executor: ExecutorService = getApplication<MainApplication>().threadPool

    fun downloadImage(url: String) {
        executor.execute {
            val stream = URL(url).openConnection().getInputStream()
            val bitmap = BitmapFactory.decodeStream(stream)
            //Log.d("MainViewModel", "By ${Thread.currentThread()}")
            mutableLiveData.postValue(bitmap)
        }
    }
}