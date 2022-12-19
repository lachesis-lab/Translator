package ru.lachesis.translator.application

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.lachesis.translator.di.application
import ru.lachesis.translator.di.historyScreen
import ru.lachesis.translator.di.mainScreen

class TranslatorApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(applicationContext)
            modules(listOf(application, mainScreen, historyScreen))
        }
    }

}