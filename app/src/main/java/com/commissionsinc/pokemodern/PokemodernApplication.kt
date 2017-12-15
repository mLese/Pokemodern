package com.commissionsinc.pokemodern

import android.app.Application
import com.commissionsinc.pokemodern.di.*


class PokemodernApplication : Application() {

    lateinit var appComponent : AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
                .appModule(AppModule(this))
                .networkUtilityModule(NetworkUtilityModule())
                .retrofitModule(RetrofitModule())
                .build()
    }
}