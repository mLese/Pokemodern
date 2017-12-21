package com.commissionsinc.pokemodern.viewmodel

import android.app.Application
import android.arch.lifecycle.*
import android.databinding.ObservableField
import com.commissionsinc.pokemodern.model.Resource
import com.commissionsinc.pokemodern.model.ResourceList
import com.commissionsinc.pokemodern.model.ResourceRepository
import com.commissionsinc.pokemodern.util.NetManager

// Extending AndroidViewModel instead of ViewModel gives us access to application context
class MainViewModel(application: Application) : AndroidViewModel(application), LifecycleObserver {

    // Instantiate repository with network manager using application context
    val repository = ResourceRepository(NetManager(application))

    // Create observable loading boolean to indicated what state ViewModel is in
    // This will be used in main activity binding to display loading indicator
    val isLoading = ObservableField<Boolean>(false)

    // Our live data list of resources which will be observed by the main activity for changes
    val resourceList = MutableLiveData<List<Resource>>()

    // Loads resources from the repository
    fun loadResources() {
        isLoading.set(true) // Set loading state while we fetch resources

        // Call the getResrouceList function defining and define the callback param
        repository.getResourceList {
            isLoading.set(false) // Not loading any more since data is fetched from repo
            resourceList.value = it.results // "it" will be the "ResourceList" passed back from repo
                                            // when this change occurs our main activity will be notified
                                            // as long as the main activity is in an appropriate lifecycle state
        }

        /* verbose definition of callback passed to repository
        repository.getResourceList(object: (ResourceList) -> Unit {
            override fun invoke(resourceListReturned: ResourceList){
                isLoading.set(false)
                resourceList.value = resourceListReturned.results
            }
        })
        */
    }

    // LifecycleObserver implementation to fetch list when LifecycleOwner enters the "created" state
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    fun initialLoad() {
        // If we have no reasource list
        if (resourceList.value == null) {
            loadResources()
        } else {
            // If resource list is non-null and empty then load resource list
            resourceList.value?.let {
                if (it.isEmpty()) {
                    loadResources()
                }
            }
        }
    }
}