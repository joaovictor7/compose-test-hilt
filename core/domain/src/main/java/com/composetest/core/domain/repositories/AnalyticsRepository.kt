package com.composetest.core.domain.repositories

import android.os.Bundle

interface AnalyticsRepository {
    suspend fun logEvent(tag: String, bundle: Bundle)
    suspend fun logNonFatalError(tag: String, throwable: Throwable, bundle: Bundle)
}