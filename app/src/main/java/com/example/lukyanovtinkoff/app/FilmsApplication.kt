package com.example.lukyanovtinkoff.app

import android.app.Application
import com.example.lukyanovtinkoff.app.di.AppComponent
import com.example.lukyanovtinkoff.app.di.DaggerAppComponent
import com.example.lukyanovtinkoff.app.di.DataModule

class FilmsApplication : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent
            .builder()
            .dataModule(DataModule(application = this@FilmsApplication))
            .build()
    }
}