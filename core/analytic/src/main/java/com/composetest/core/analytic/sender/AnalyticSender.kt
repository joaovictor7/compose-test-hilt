package com.composetest.core.analytic.sender

import com.composetest.core.analytic.event.AnalyticEvent
import com.composetest.core.analytic.event.ErrorAnalyticEvent

interface AnalyticSender {
    suspend fun sendEvent(event: AnalyticEvent)
    suspend fun sendErrorEvent(event: ErrorAnalyticEvent)
}