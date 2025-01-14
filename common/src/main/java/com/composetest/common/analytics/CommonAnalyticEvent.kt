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

    open class CLickButton(
        analyticScreen: AnalyticScreen,
        buttonName: String,
    ) : CommonAnalyticEvent(analyticScreen) {
        override val tag = "click"
        override val params = mapOf(
            "button_name" to buttonName
        )
    }
}