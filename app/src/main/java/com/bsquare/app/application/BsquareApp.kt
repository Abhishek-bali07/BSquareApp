package com.bsquare.app.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class BSquareApp: Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this


    }

    companion object {
        lateinit var appInstance: BSquareApp
    }


}