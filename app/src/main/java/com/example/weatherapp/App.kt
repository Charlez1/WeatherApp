package com.example.weatherapp

import android.app.Application
import android.content.res.Resources
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class App: Application() {

    companion object {
        lateinit var resourses: Resources
    }

    override fun onCreate() {
        super.onCreate()
        resourses = resources
    }
}