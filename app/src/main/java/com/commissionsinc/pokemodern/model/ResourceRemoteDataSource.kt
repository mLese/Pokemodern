package com.commissionsinc.pokemodern.model

import android.os.Handler

// Remote data source for internet access
class ResourceRemoteDataSource {
    // (ResourceList) -> Unit demonstrates using a function as an argument
    // "callback" takes a ResourceList as a parameter and has no return value
    fun getResourceList(callback: (ResourceList) -> Unit) {
        // Set up list of resources
        val resources = listOf(
                Resource("bulbasaur", "https://pokeapi.co/api/v2/pokemon/1/"),
                Resource("ivysaur", "https://pokeapi.co/api/v2/pokemon/2/"),
                Resource("venusaur", "https://pokeapi.co/api/v2/pokemon/3/")
        )

        // Add resources to resource list
        val resourceList = ResourceList(151, null, null, resources)

        // Simulate delay and trigger callback with list
        Handler().postDelayed({callback(resourceList)}, 2000)
    }
}
