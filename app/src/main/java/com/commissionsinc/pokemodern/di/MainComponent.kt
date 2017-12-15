package com.commissionsinc.pokemodern.di

import com.commissionsinc.pokemodern.view.MainActivity
import dagger.Subcomponent


@Subcomponent(modules = [ResourceRepositoryModule::class, MainModule::class])
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}