package com.commissionsinc.pokemodern.model

import android.os.Handler

// Local data source for offline access
class ResourceLocalDataSource {
    // (ResourceList) -> Unit demonstrates using a function as an argument
    // "callback" takes a ResourceList as a parameter and has no return value
    fun getResourceList(callback: (ResourceList) -> Unit) {
        // Set up list of resources
        val resources = listOf(
                Resource("charmander", "https://pokeapi.co/api/v2/pokemon/4/"),
                Resource("charmeleon", "https://pokeapi.co/api/v2/pokemon/5/"),
                Resource("charizard", "https://pokeapi.co/api/v2/pokemon/6/")
        )

        // Add resources to resource list
        val resourceList = ResourceList(151, null, null, resources)

        // Simulate delay and trigger callback with list
        Handler().postDelayed({callback(resourceList)}, 2000)
    }
}
