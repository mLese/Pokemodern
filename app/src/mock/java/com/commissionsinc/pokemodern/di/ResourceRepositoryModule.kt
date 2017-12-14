package com.commissionsinc.pokemodern.di

import com.commissionsinc.pokemodern.model.*
import com.commissionsinc.pokemodern.util.NetManager
import dagger.Module
import dagger.Provides

@Module
class ResourceRepositoryModule {

    @Provides
    fun provideLocalDataSource(): LocalResourceDataSource = ResourceLocalDataSource()

    @Provides
    fun provideRemoteDataSource(): RemoteResourceDataSource = ResourceRemoteDataSource()

    @Provides
    fun provideResourceRepository(netManager: NetManager,
                                  localDataSource: LocalResourceDataSource,
                                  remoteDataSource: RemoteResourceDataSource) : ResourceRepository =
            ResourceRepository(netManager, localDataSource, remoteDataSource)
}