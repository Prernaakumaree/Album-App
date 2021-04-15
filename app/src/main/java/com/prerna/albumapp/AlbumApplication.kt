package com.prerna.albumapp

import android.app.Application
import com.facebook.stetho.Stetho
import com.prerna.albumapp.di.dataModule
import com.prerna.albumapp.di.networkModule
import com.prerna.albumapp.di.presenterModule
import com.prerna.albumapp.di.useCaseModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AlbumApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@AlbumApplication)
            modules(networkModule, dataModule, useCaseModule, presenterModule)
        }

        if (BuildConfig.DEBUG) {
            Stetho.initializeWithDefaults(this)
        }
    }
}