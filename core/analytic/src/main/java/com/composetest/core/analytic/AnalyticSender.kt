package com.composetest.core.analytic

import com.composetest.core.analytic.event.ErrorAnalyticEvent

interface AnalyticSender {
    suspend fun sendEvent(event: AnalyticEvent)
    suspend fun sendErrorEvent(event: ErrorAnalyticEvent)
}