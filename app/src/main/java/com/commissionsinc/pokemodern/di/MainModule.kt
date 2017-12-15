package com.commissionsinc.pokemodern.di

import com.commissionsinc.pokemodern.model.ResourceRepository
import com.commissionsinc.pokemodern.viewmodel.MainViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class MainModule {
    @Provides
    fun provideMainViewModelFactory(repository: ResourceRepository) = MainViewModelFactory(repository)
}