package com.commissionsinc.pokemodern.model

import android.os.Handler


class ResourceLocalDataSource {
    fun getResourceList(callback: (ResourceList) -> Unit) {
        val resources = listOf(
                Resource("charmander", "https://pokeapi.co/api/v2/pokemon/4/"),
                Resource("charmeleon", "https://pokeapi.co/api/v2/pokemon/5/"),
                Resource("charizard", "https://pokeapi.co/api/v2/pokemon/6/")
        )
        val resourceList = ResourceList(1, null, null, resources)
        Handler().postDelayed({callback(resourceList)}, 2000)
    }
}
