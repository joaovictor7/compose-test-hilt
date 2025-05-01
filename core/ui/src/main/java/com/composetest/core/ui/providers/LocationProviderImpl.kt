package com.composetest.core.ui.providers

import android.annotation.SuppressLint
import android.content.Context
import com.composetest.common.errors.LocationError
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

internal class LocationProviderImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : LocationProvider {

    private val fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)

    @SuppressLint("MissingPermission")
    override suspend fun getLastLocation() = suspendCancellableCoroutine {
        fusedLocationClient.getCurrentLocation(Priority.PRIORITY_HIGH_ACCURACY, null)
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
}