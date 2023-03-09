package com.example.composeapplication

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class MyComposeApp: Application() {

    override fun onCreate() {
        super.onCreate()
    }


}