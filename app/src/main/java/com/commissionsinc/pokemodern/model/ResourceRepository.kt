package com.commissionsinc.pokemodern.model

import com.commissionsinc.pokemodern.util.NetManager


class ResourceRepository(val netManager: NetManager,
                         val localDataSource: LocalResourceDataSource,
                         val remoteDataSource: RemoteResourceDataSource){

    fun getResourceList(callback: (ResourceList) -> Unit) {
        if (netManager.isConnectedToInternet) {
            remoteDataSource.getResourceList { callback(it) }
        } else {
            localDataSource.getResourceList { callback(it) }
        }
    }
}

interface LocalResourceDataSource {
    fun getResourceList(callback: (ResourceList) -> Unit)
    fun saveResourceList(resourceList: ResourceList)
}

interface RemoteResourceDataSource {
    fun getResourceList(callback: (ResourceList) -> Unit)
}
