package com.commissionsinc.pokemodern.model

import android.util.Log
import com.commissionsinc.pokemodern.model.api.ResourceService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class RetrofitRemoteResourceDataSource(val resourceService: ResourceService): RemoteResourceDataSource {

    override fun getResourceList(callback: (ResourceList) -> Unit) {
        resourceService.getResourceList().enqueue(object: Callback<ResourceList> {
            override fun onResponse(call: Call<ResourceList>?, response: Response<ResourceList>?) {
                if (response?.body() != null)
                    callback(response.body() as ResourceList)
            }

            override fun onFailure(call: Call<ResourceList>?, t: Throwable?) {
                Log.e("Retrofit", t?.toString())
            }
        })
    }
}