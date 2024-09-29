package com.composetest.core.data.repositories

import android.os.Bundle
import com.composetest.core.data.datasources.remote.FirebaseAnalyticsDataSource
import com.composetest.core.domain.repositories.AnalyticsRepository
import javax.inject.Inject

internal class AnalyticsRepositoryImpl @Inject constructor(
    private val analyticsDataSource: FirebaseAnalyticsDataSource
) : AnalyticsRepository {

    override suspend fun logEvent(tag: String, bundle: Bundle) {
        analyticsDataSource.logEvent(tag, bundle)
    }

    override suspend fun logNonFatalError(tag: String, throwable: Throwable, bundle: Bundle) {
        analyticsDataSource.logNonFatalError(tag, throwable, bundle)
    }
}