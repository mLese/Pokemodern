package com.commissionsinc.pokemodern.model


data class ResourceList(val count: Int, val next: String?, val previous: String?, val results: List<Resource>)
