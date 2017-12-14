package com.commissionsinc.pokemodern.viewmodel

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.commissionsinc.pokemodern.di.AppComponent


class MainViewModelFactory(val appComponent: AppComponent): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(appComponent) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}