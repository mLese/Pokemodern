package com.commissionsinc.pokemodern.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.MutableLiveData
import android.databinding.ObservableField
import com.commissionsinc.pokemodern.model.Resource
import com.commissionsinc.pokemodern.model.ResourceRepository
import com.commissionsinc.pokemodern.util.NetManager


class MainViewModel(application: Application) : AndroidViewModel(application) {

    val repository = ResourceRepository(NetManager(application))
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