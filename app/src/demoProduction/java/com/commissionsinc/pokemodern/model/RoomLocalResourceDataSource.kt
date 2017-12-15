package com.commissionsinc.pokemodern.model

import android.os.Handler


class RoomLocalResourceDataSource: LocalResourceDataSource{
    override fun getResourceList(callback: (ResourceList) -> Unit) {
        val resources = listOf(
                Resource("charmander", "https://pokeapi.co/api/v2/pokemon/4/"),
                Resource("charmeleon", "https://pokeapi.co/api/v2/pokemon/5/"),
                Resource("charizard", "https://pokeapi.co/api/v2/pokemon/6/")
        )
        val resourceList = ResourceList(1, null, null, resources)
        Handler().postDelayed({callback(resourceList)}, 2000)
    }

    override fun saveResourceList(resourceList: ResourceList) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}