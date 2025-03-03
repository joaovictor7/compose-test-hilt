package com.composetest.core.analytic.events

import com.composetest.core.analytic.AnalyticEvent
import com.composetest.core.analytic.AnalyticScreen

open class ErrorAnalyticEvent(
    val error: Throwable,
    analyticScreen: AnalyticScreen = object : AnalyticScreen {
        override val screen = null
    }
) : AnalyticEvent, AnalyticScreen by analyticScreen {
    final override val tag = "error"
    final override val params = mapOf(
        "message" to (error.message ?: "No error message")
    )
}
