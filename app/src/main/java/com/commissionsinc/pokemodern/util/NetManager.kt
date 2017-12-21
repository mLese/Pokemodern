package com.commissionsinc.pokemodern.util

import android.content.Context
import android.net.ConnectivityManager

// Net manager requires application context to check network state
class NetManager(private var applicationContext: Context) {
    // Property that will tell us if device is connected to the internet or not
    val isConnectedToInternet: Boolean
        get() {
            // Cast as connectivity manager
            val connectionManager = applicationContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            // Return network status as boolean
            val networkInfo = connectionManager.activeNetworkInfo
            return networkInfo != null && networkInfo.isConnected
        }
}