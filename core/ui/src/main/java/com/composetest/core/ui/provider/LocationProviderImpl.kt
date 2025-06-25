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
import kotlin.coroutines.suspendCoroutine
import kotlin.time.Duration.Companion.seconds

internal class LocationProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
) : LocationProvider {

    private val fusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
    }
    private val client by lazy { LocationServices.getSettingsClient(context) }
    private val builder by lazy {
        LocationSettingsRequest.Builder()
            .addLocationRequest(
                LocationRequest.Builder(ACCURACY, locationRequestDelay.inWholeSeconds).build()
            )
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

    override suspend fun isLocationEnabled() = suspendCoroutine { coroutine ->
        client.checkLocationSettings(builder)
            .addOnSuccessListener {
                coroutine.resume(true)
            }.addOnFailureListener {
                coroutine.resume(false)
            }
    }

    private companion object {
        const val ACCURACY = Priority.PRIORITY_HIGH_ACCURACY
        val locationRequestDelay = 10.seconds
    }
}