package com.composetest.core.analytic.api.sender

import com.composetest.core.analytic.api.event.AnalyticEvent
import com.composetest.core.analytic.api.event.ErrorAnalyticEvent

interface AnalyticSender {
    suspend fun sendEvent(event: AnalyticEvent)
    suspend fun sendErrorEvent(event: ErrorAnalyticEvent)
}