package com.commissionsinc.pokemodern.di

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class, NetworkUtilityModule::class, RetrofitModule::class])
interface AppComponent {
    fun mainComponent(): MainComponent
}
