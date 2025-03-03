package com.composetest.core.analytic.events

import com.composetest.core.analytic.AnalyticEvent
import com.composetest.core.analytic.AnalyticScreen

sealed class CommonAnalyticEvent(
    analyticScreen: AnalyticScreen
) : AnalyticEvent, AnalyticScreen by analyticScreen {
    data class OpenScreen(
        private val analyticScreen: AnalyticScreen
    ) : CommonAnalyticEvent(analyticScreen) {
        override val tag = "screen_view"
        override val params: Map<String, Any> = emptyMap()
    }
}