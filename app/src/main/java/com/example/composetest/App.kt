package com.example.composetest

import android.app.Application
import com.example.composetest.di.AppComponent
import com.example.composetest.di.modules.NetworkModule

class App : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
            .networkModule(NetworkModule())
            .build()
    }

}