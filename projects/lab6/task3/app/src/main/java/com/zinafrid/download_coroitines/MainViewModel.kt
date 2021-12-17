package com.zinafrid.download_coroitines

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.net.URL

class MainViewModel : ViewModel(){

    val mutableLiveData = MutableLiveData<Bitmap>()

    fun downloadImage(url: String) {
        viewModelScope.launch(Dispatchers.IO){
            val stream = URL(url).openConnection().getInputStream()
            val bitmap = BitmapFactory.decodeStream(stream)
            withContext(Dispatchers.Main) {
                mutableLiveData.value = bitmap
            }
        }
    }
}