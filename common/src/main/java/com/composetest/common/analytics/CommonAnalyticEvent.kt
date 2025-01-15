package com.composetest.common.analytics

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