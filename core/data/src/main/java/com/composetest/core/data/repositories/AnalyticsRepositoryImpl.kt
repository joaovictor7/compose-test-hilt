package com.composetest.core.data.repositories

import android.os.Bundle
import com.composetest.core.data.datasources.remote.AnalyticsDataSource
import com.composetest.core.domain.repositories.AnalyticsRepository
import javax.inject.Inject

internal class AnalyticsRepositoryImpl @Inject constructor(
    private val analyticsDataSource: AnalyticsDataSource
) : AnalyticsRepository {

    override fun logEvent(tag: String, bundle: Bundle) {
        analyticsDataSource.logEvent(tag, bundle)
    }

    override fun logNonFatalError(tag: String, throwable: Throwable, bundle: Bundle) {
        analyticsDataSource.logNonFatalError(tag, throwable, bundle)
    }
}