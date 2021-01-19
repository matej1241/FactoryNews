package com.matej.factorynews

import android.app.Application

class FactoryNews: Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object{
        lateinit var instance: FactoryNews
            private set
    }
}