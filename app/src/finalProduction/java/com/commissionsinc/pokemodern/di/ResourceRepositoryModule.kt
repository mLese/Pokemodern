package com.commissionsinc.pokemodern.di

import com.commissionsinc.pokemodern.model.*
import com.commissionsinc.pokemodern.model.api.BaseUrl
import com.commissionsinc.pokemodern.model.api.ResourceService
import com.commissionsinc.pokemodern.util.NetManager
import dagger.Module
import dagger.Provides


@Module class ResourceRepositoryModule {

    @Provides
    fun provideBaseUrl(): BaseUrl = BaseUrl("http://pokeapi.co/")

    @Provides
    fun provideLocalDataSource(): LocalResourceDataSource = RoomLocalResourceDataSource()

    @Provides
    fun provideRemoteDataSource(resourceService: ResourceService): RemoteResourceDataSource = RetrofitRemoteResourceDataSource(resourceService)

    @Provides
    fun provideResourceRepository(netManager: NetManager,
                                  localDataSource: LocalResourceDataSource,
                                  remoteDataSource: RemoteResourceDataSource): ResourceRepository =
            ResourceRepository(netManager, localDataSource, remoteDataSource)
}