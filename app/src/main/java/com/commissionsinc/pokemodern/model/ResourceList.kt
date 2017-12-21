package com.commissionsinc.pokemodern.model

// Simple kotlin data class for holding resource list returned from repository
data class ResourceList(val count: Int, val next: String?, val previous: String?, val results: List<Resource>)
