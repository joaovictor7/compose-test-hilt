package com.composetest.common.error

sealed class LocationError : Throwable() {
    class LocationNotFound : LocationError() {
        override val message: String = "Location not found"
    }

    class LocationUnknownError(error: Throwable) : LocationError() {
        override val cause: Throwable = error
    }
}