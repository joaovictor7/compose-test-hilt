package com.composetest.core.domain.models.analytics

import com.composetest.core.domain.interfaces.analytics.AnalyticEvent
import com.composetest.core.domain.interfaces.analytics.AnalyticScreen

open class ErrorAnalyticEvent(
    val throwable: Throwable,
    analyticScreen: AnalyticScreen = object : AnalyticScreen {
        override val screen = null
    }
) : AnalyticEvent, AnalyticScreen by analyticScreen {
    final override val tag = "error"
    final override val params = mapOf(
        "message" to (throwable.message ?: "No error message")
    )
}
