package com.skylabstechke.foody.utils

import android.net.ConnectivityManager
import android.net.Network
import kotlinx.coroutines.flow.MutableStateFlow

class NetworkConnection : ConnectivityManager.NetworkCallback() {
   var isNetworkAvailable = MutableStateFlow(false)

    override fun onAvailable(network: Network) {
        super.onAvailable(network)
    }

    override fun onLost(network: Network) {
        super.onLost(network)
    }
}