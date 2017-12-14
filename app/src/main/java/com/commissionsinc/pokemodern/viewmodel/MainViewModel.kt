package com.commissionsinc.pokemodern.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.databinding.ObservableField
import com.commissionsinc.pokemodern.di.AppComponent
import com.commissionsinc.pokemodern.model.Resource
import com.commissionsinc.pokemodern.model.ResourceRepository
import javax.inject.Inject

class MainViewModel(appComponent: AppComponent) : ViewModel() {

     init {
         appComponent.inject(this)
     }

    @Inject
    lateinit var repository: ResourceRepository

    val resourceList = MutableLiveData<List<Resource>>()
    val isLoading = ObservableField<Boolean>(false)

    fun loadResources() {
        isLoading.set(true)
        repository.getResourceList {
            isLoading.set(false)
            resourceList.value = it.results
        }
    }
}