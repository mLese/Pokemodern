package com.commissionsinc.pokemodern.model

import com.commissionsinc.pokemodern.util.NetManager


class ResourceRepository(val netManager: NetManager){

    val localDataSource = ResourceLocalDataSource()
    val remoteDataSource = ResourceRemoteDataSource()

    fun getResourceList(callback: (ResourceList) -> Unit) {
        if (netManager.isConnectedToInternet) {
            remoteDataSource.getResourceList { callback(it) }
        } else {
            localDataSource.getResourceList { callback(it) }
        }
    }
}
