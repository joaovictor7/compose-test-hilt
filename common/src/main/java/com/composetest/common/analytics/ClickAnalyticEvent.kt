package com.composetest.common.analytics

import com.composetest.common.analytics.interfaces.AnalyticEvent
import com.composetest.common.analytics.interfaces.AnalyticScreen

open class ClickAnalyticEvent(
    clickEvent: String,
    analyticScreen: AnalyticScreen
) : AnalyticEvent, AnalyticScreen by analyticScreen {
    final override val tag = "click"
    final override val params = mapOf(
        "clicked" to clickEvent
    )
}