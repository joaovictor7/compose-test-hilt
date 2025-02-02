package com.composetest.core.ui.enums

import android.Manifest

enum class Permission(val manifest: String) {
    FINE_LOCALIZATION(Manifest.permission.ACCESS_FINE_LOCATION),
    COARSE_LOCALIZATION(Manifest.permission.ACCESS_COARSE_LOCATION);

    companion object {
        val localization = listOf(FINE_LOCALIZATION, COARSE_LOCALIZATION)
    }
}