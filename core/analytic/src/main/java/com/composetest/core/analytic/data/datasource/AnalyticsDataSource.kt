package com.composetest.core.analytic.data.datasource

import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import javax.inject.Inject

internal class AnalyticsDataSource @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics,
    private val firebaseCrashlytics: FirebaseCrashlytics,
) {

    fun logEvent(tag: String, params: Bundle) {
        firebaseAnalytics.logEvent(tag, params)
    }

    fun logNonFatalError(tag: String, throwable: Throwable, params: Bundle) {
        firebaseAnalytics.logEvent(tag, params)
        firebaseCrashlytics.recordException(throwable)
    }
}