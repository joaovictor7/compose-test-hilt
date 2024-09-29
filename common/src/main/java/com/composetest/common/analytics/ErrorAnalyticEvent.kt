package com.composetest.common.analytics

import com.composetest.common.analytics.interfaces.AnalyticEvent
import com.composetest.common.analytics.interfaces.AnalyticScreen

open class ErrorAnalyticEvent(
    val throwable: Throwable,
    analyticScreen: AnalyticScreen = object : AnalyticScreen {}
) : AnalyticEvent, AnalyticScreen by analyticScreen {
    final override val tag = "error"
    final override val params = mapOf(
        "message" to (throwable.message ?: "No error message")
    )
}
