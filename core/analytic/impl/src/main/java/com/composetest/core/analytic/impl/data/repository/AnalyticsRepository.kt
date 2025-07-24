package com.composetest.core.analytic.impl.data.repository

import android.os.Bundle
import com.composetest.core.analytic.impl.data.datasource.AnalyticsDataSource
import javax.inject.Inject

internal class AnalyticsRepository @Inject constructor(
    private val analyticsDataSource: AnalyticsDataSource
) {

    fun logEvent(tag: String, bundle: Bundle) {
        analyticsDataSource.logEvent(tag, bundle)
    }

    fun logNonFatalError(tag: String, throwable: Throwable, bundle: Bundle) {
        analyticsDataSource.logNonFatalError(tag, throwable, bundle)
    }
}