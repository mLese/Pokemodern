package com.commissionsinc.pokemodern.di

import android.app.Application
import dagger.Module
import dagger.Provides


@Module class AppModule(val app: Application) {
    @Provides
    fun getApplication() : Application {
        return app
    }
}