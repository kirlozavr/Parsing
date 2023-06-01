package com.example.parsing

import android.app.Application
import com.example.parsing.di.AppComponent
import com.example.parsing.di.DaggerAppComponent

class ParsingApplication: Application() {

    val component: AppComponent by lazy {
        DaggerAppComponent.builder().context(this).build()
    }

    override fun onCreate() {
        component.inject(this)
        super.onCreate()
    }
}