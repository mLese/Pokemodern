package com.commissionsinc.pokemodern.model

import com.commissionsinc.pokemodern.util.NetManager

// Resource repository needs a NetManager to check for online/offline
class ResourceRepository(val netManager: NetManager){

    // Create local and remote data source objects to fetch resource list
    val localDataSource = ResourceLocalDataSource()
    val remoteDataSource = ResourceRemoteDataSource()

    // Function parameter "callback" takes ResourceList parameter and returns nothing
    fun getResourceList(callback: (ResourceList) -> Unit) {
        // If connected to internet use the remote list else use the local list
        if (netManager.isConnectedToInternet) {
            remoteDataSource.getResourceList { callback(it) }
        } else {
            localDataSource.getResourceList { callback(it) }
        }
    }
}
