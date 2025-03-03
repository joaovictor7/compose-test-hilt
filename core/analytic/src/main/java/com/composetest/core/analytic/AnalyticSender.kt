package com.composetest.core.analytic

import com.composetest.core.analytic.events.ErrorAnalyticEvent

interface AnalyticSender {
    suspend fun sendEvent(event: AnalyticEvent)
    suspend fun sendErrorEvent(event: ErrorAnalyticEvent)
}