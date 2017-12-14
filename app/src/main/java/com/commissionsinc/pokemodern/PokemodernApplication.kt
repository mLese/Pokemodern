package com.commissionsinc.pokemodern

import android.app.Application
import com.commissionsinc.pokemodern.di.AppComponent
import com.commissionsinc.pokemodern.di.AppModule
import com.commissionsinc.pokemodern.di.DaggerAppComponent
import com.commissionsinc.pokemodern.di.NetworkUtilityModule


class PokemodernApplication : Application() {

    lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkUtilityModule(NetworkUtilityModule())
                .build()
        /*
        appComponent = DaggerAppComponent.builder()
                .appModule(this)
                .build()
                */
    }
}