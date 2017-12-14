package com.commissionsinc.pokemodern.di

import android.app.Application
import com.commissionsinc.pokemodern.util.NetManager
import dagger.Module
import dagger.Provides

@Module
class NetworkUtilityModule {
    @Provides
    fun provideNetManager(context: Application): NetManager = NetManager(context)
}