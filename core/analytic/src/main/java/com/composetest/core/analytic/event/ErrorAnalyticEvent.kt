package com.composetest.core.analytic.event

import com.composetest.core.analytic.event.AnalyticEvent
import com.composetest.core.analytic.screen.ScreenAnalytic

open class ErrorAnalyticEvent(
    val error: Throwable,
    screenAnalytic: ScreenAnalytic = object : ScreenAnalytic {
        override val screen = null
    }
) : AnalyticEvent, ScreenAnalytic by screenAnalytic {
    final override val tag = "error"
    final override val params = mapOf(
        "message" to (error.message ?: "No error message")
    )
}
