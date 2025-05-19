package com.composetest.core.domain.repository

import android.os.Bundle

interface AnalyticsRepository {
    fun logEvent(tag: String, bundle: Bundle)
    fun logNonFatalError(tag: String, throwable: Throwable, bundle: Bundle)
}