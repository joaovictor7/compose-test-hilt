package com.composetest.core.ui.provider

import android.location.Location

interface LocationProvider {
    suspend fun getLastLocation(): Location
}