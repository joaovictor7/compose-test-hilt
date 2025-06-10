package com.composetest.core.ui.provider

import android.annotation.SuppressLint
import android.content.Context
import com.composetest.common.error.LocationError
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.Priority
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.time.Duration.Companion.seconds

internal class LocationProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : LocationProvider {

    private val fusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }
    private val client by lazy { LocationServices.getSettingsClient(context) }
    private val builder by lazy {
        val locationRequest =
            LocationRequest.Builder(ACCURACY, locationRequestDelay.inWholeSeconds).build()
        LocationSettingsRequest.Builder()
            .addLocationRequest(locationRequest)
            .build()
    }

    @SuppressLint("MissingPermission")
    override suspend fun getCurrentLocation() = suspendCancellableCoroutine {
        fusedLocationClient.getCurrentLocation(ACCURACY, null)
            .addOnSuccessListener { location ->
                if (location != null) {
                    it.resume(location)
                } else {
                    it.resumeWithException(LocationError.LocationNotFound())
                }
            }.addOnFailureListener { error ->
                it.resumeWithException(LocationError.LocationUnknownError(error))
            }
    }

    override suspend fun isLocationEnabled() = suspendCancellableCoroutine { coroutine ->
        client.checkLocationSettings(builder)
            .addOnSuccessListener {
                coroutine.resume(true)
            }.addOnFailureListener { error ->
                coroutine.resumeWithException(error)
            }
    }

    private companion object {
        const val ACCURACY = Priority.PRIORITY_HIGH_ACCURACY
        val locationRequestDelay = 10.seconds
    }
}