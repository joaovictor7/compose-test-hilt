package com.composetest.core.analytic.sender

import android.os.Bundle
import androidx.core.os.bundleOf
import com.composetest.core.analytic.data.repository.AnalyticsRepository
import com.composetest.core.analytic.event.AnalyticEvent
import com.composetest.core.analytic.event.ErrorAnalyticEvent
import com.composetest.core.domain.provider.BuildConfigProvider
import com.composetest.core.domain.repository.UserRepository
import java.time.ZonedDateTime
import javax.inject.Inject

internal class AnalyticSenderImpl @Inject constructor(
    private val analyticsRepository: AnalyticsRepository,
    private val userRepository: UserRepository,
    private val buildConfigProvider: BuildConfigProvider
) : AnalyticSender {

    override suspend fun sendEvent(event: AnalyticEvent) {
        val bundle = createBundle(event)
        analyticsRepository.logEvent(event.tag, bundle)
    }

    override suspend fun sendErrorEvent(event: ErrorAnalyticEvent) {
        val bundle = createBundle(event)
        analyticsRepository.logNonFatalError(event.tag, event.error, bundle)
    }

    private suspend fun createBundle(event: AnalyticEvent): Bundle {
        val user = userRepository.getCurrentUser()
        return bundleOf(
            USER_ID to user?.id.orEmpty(),
            LOGGED_SESSION to (user != null).toString(),
            DATE_TIME to ZonedDateTime.now().toString(),
            APP_VERSION to buildConfigProvider.buildConfig.versionName,
            FEATURE to event.feature,
            ANDROID_SDK_VERSION to buildConfigProvider.buildConfig.androidSdkVersion.toString(),
            platform,
            *event.params.map { it.key to it.value.toString() }.toTypedArray()
        ).apply {
            event.screen?.let { putString(SCREEN, it) }
        }
    }

    private companion object {
        const val USER_ID = "user_id"
        const val LOGGED_SESSION = "logged_session"
        const val DATE_TIME = "date_time"
        const val APP_VERSION = "app_version"
        const val ANDROID_SDK_VERSION = "android_sdk_version"
        const val SCREEN = "screen"
        const val FEATURE = "feature"
        val platform = "platform" to "android"
    }
}