package com.composetest.core.domain.analytics

import com.composetest.core.domain.interfaces.AnalyticEvent
import com.composetest.core.domain.interfaces.AnalyticScreen

open class ClickAnalyticEvent(
    clickEvent: String,
    analyticScreen: AnalyticScreen
) : AnalyticEvent, AnalyticScreen by analyticScreen {
    final override val tag = "click"
    final override val params = mapOf(
        "clicked" to clickEvent
    )
}