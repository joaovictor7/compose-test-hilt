package com.composetest.common.enums

import android.Manifest

enum class Permission(val manifest: List<String>) {
    LOCALIZATION(
        listOf(
            Manifest.permission.ACCESS_FINE_LOCATION,
            Manifest.permission.ACCESS_COARSE_LOCATION,
        )
    )
}