package com.composetest.core.data.provider

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.composetest.common.provider.NetworkProvider
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class NetworkProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
) : NetworkProvider {

    override val internetIsConnected: Boolean
        get() {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network = connectivityManager.activeNetwork ?: return false
            val networkCapabilities =
                connectivityManager.getNetworkCapabilities(network) ?: return false
            return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                    networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
        }
}