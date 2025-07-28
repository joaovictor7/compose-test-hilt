package com.composetest.core.ui.provider

import android.annotation.SuppressLint
import android.content.Context
import android.location.LocationManager
import com.composetest.common.error.LocationError
import com.composetest.common.extension.orFalse
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.suspendCancellableCoroutine
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

internal class LocationProviderImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
) : LocationProvider {

    private val fusedLocationClient by lazy {
        LocationServices.getFusedLocationProviderClient(context)
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

    override suspend fun isLocationEnabled(): Boolean {
        val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as? LocationManager
        return locationManager?.isLocationEnabled.orFalse
    }

    private companion object {
        const val ACCURACY = Priority.PRIORITY_HIGH_ACCURACY
    }
}