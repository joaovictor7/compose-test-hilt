package com.composetest.core.data.providers

import android.annotation.SuppressLint
import android.content.Context
import com.composetest.common.errors.LocationError
import com.composetest.common.providers.DispatcherProvider
import com.composetest.common.providers.LocationProvider
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.withContext
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

internal class LocationProviderImpl @Inject constructor(
    private val dispatcherProvider: DispatcherProvider,
    @ApplicationContext private val context: Context
) : LocationProvider {

    private val fusedLocationClient: FusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override suspend fun getLastLocation() =
        withContext(dispatcherProvider.io) {
            suspendCoroutine {
                val result = fusedLocationClient.lastLocation.result
                    ?: throw LocationError.LocationNotFound()
                it.resume(result)
            }
        }
}