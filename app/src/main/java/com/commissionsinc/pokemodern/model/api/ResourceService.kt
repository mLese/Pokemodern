package com.commissionsinc.pokemodern.model.api

import com.commissionsinc.pokemodern.model.ResourceList
import retrofit2.Call
import retrofit2.http.GET


interface ResourceService {
    @GET("/api/v2/pokemon/")
    fun getResourceList(): Call<ResourceList>
}