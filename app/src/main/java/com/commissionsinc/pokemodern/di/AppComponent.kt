package com.commissionsinc.pokemodern.di

import com.commissionsinc.pokemodern.viewmodel.MainViewModel
import dagger.Component

@Component(modules = [AppModule::class, NetworkUtilityModule::class, ResourceRepositoryModule::class])
interface AppComponent {
    fun inject(viewModel: MainViewModel)
}
