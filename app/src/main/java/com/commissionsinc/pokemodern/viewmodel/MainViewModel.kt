package com.commissionsinc.pokemodern.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import android.databinding.ObservableField
import android.util.Log
import com.commissionsinc.pokemodern.model.Resource
import com.commissionsinc.pokemodern.model.ResourceRepository
import com.commissionsinc.pokemodern.util.NetManager


class MainViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {

    val repository = ResourceRepository(NetManager(application))
    val isLoading = ObservableField<Boolean>(false)
    val resourceList = MutableLiveData<List<Resource>>()

    fun loadResources() {
        isLoading.set(true)
        repository.getResourceList {
            isLoading.set(false)
            resourceList.value = it.results
        }
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun initialLoad() {
        if (resourceList.value == null) {
            loadResources()
        } else {
            resourceList.value?.let {
                if (it.isEmpty()) {
                    loadResources()
                }
            }
        }
    }
}