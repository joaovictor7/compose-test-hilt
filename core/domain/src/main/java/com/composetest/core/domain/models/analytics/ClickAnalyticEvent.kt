package com.composetest.core.domain.models.analytics

import com.composetest.core.domain.interfaces.analytics.AnalyticEvent
import com.composetest.core.domain.interfaces.analytics.AnalyticScreen

open class ClickAnalyticEvent(
    clickEvent: String,
    analyticScreen: AnalyticScreen
) : AnalyticEvent, AnalyticScreen by analyticScreen {
    final override val tag = "click"
    final override val params = mapOf(
        "clicked" to clickEvent
    )
}