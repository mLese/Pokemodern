package com.commissionsinc.pokemodern.model

import android.os.Handler


class ResourceRemoteDataSource {
    fun getResourceList(callback: (ResourceList) -> Unit) {
        val resources = listOf(
                Resource("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"),
                Resource("ivysaur", "https://pokeapi.co/api/v2/pokemon/2/"),
                Resource("venusaur", "https://pokeapi.co/api/v2/pokemon/3/")
        )
        val resourceList = ResourceList(151, null, null, resources)
        Handler().postDelayed({callback(resourceList)}, 2000)
    }
}
