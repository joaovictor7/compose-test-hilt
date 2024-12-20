package com.composetest.core.data.datasources.remote

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import javax.inject.Inject

internal class AnalyticsDataSourceImpl @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics,
    private val firebaseCrashlytics: FirebaseCrashlytics,
) : AnalyticsDataSource {

    override fun logEvent(tag: String, params: Bundle) {
        firebaseAnalytics.logEvent(tag, params)
    }

    override fun logNonFatalError(tag: String, throwable: Throwable, params: Bundle) {
        firebaseAnalytics.logEvent(tag, params)
        firebaseCrashlytics.recordException(throwable)
    }
}