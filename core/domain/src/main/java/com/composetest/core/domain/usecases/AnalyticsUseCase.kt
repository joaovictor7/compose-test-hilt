package com.composetest.core.domain.usecases

import com.composetest.common.analytics.ErrorAnalyticEvent
import com.composetest.common.analytics.interfaces.AnalyticEvent
import com.composetest.core.domain.repositories.AnalyticsRepository
import javax.inject.Inject

class AnalyticsUseCase @Inject constructor(
    private val analyticsRepository: AnalyticsRepository
) {

    suspend operator fun invoke(event: AnalyticEvent) {
        analyticsRepository.logEvent(event)
    }

    suspend operator fun invoke(event: ErrorAnalyticEvent) {
        analyticsRepository.logNonFatalError(event)
    }
}