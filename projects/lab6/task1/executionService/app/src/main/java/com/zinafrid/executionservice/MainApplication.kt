package com.zinafrid.executionservice

import android.app.Application
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

class MainApplication: Application() {
    val threadPool: ExecutorService = Executors.newFixedThreadPool(1)
}