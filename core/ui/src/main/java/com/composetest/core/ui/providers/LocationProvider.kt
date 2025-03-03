package com.composetest.core.ui.providers

import android.location.Location

interface LocationProvider {
    suspend fun getLastLocation(): Location
}