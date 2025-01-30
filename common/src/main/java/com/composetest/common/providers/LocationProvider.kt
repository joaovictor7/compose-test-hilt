package com.composetest.common.providers

import android.location.Location

interface LocationProvider {
    suspend fun getLastLocation(): Location
}