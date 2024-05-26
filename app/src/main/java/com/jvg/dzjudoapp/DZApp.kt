package com.jvg.dzjudoapp

import android.app.Application
import com.jvg.dzjudoapp.core.di.Koin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class DZApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Koin().initKoin(
            additionalModules = listOf()
        ) {
            androidContext(this@DZApp)
            androidLogger(level = Level.DEBUG)
        }
    }
}
