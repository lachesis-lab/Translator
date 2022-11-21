package ru.lachesis.translator.application

import android.app.Application
import org.koin.core.context.startKoin
import ru.lachesis.translator.di.application
import ru.lachesis.translator.di.mainScreen

class TranslatorApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(listOf(application, mainScreen))
        }
    }

}