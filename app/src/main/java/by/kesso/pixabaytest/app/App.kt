package by.kesso.pixabaytest.app

import android.app.Application
import by.kesso.pixabaytest.BuildConfig
import by.kesso.pixabaytest.domain.di.usecase
import by.kesso.pixabaytest.repository.repository
import by.kesso.pixabaytest.ui.di.viewmodel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)

            modules(
                usecase,
                repository,
                viewmodel,
            )
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}