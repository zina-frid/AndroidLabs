package com.zinafrid.downloader_ex_se

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.net.URL
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainViewModel : ViewModel(){

    val mutableLiveData = MutableLiveData<Bitmap>()
    private lateinit var executor: ExecutorService

    fun downloadImage(url: String) {
        executor = Executors.newFixedThreadPool(1)
        executor.execute {
            val stream = URL(url).openConnection().getInputStream()
            val bitmap = BitmapFactory.decodeStream(stream)
            mutableLiveData.postValue(bitmap)
        }
    }

    override fun onCleared() {
        executor.shutdown()
        super.onCleared()
    }
}