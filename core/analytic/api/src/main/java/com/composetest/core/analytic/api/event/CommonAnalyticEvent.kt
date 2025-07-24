package com.composetest.core.analytic.api.event

import com.composetest.core.analytic.api.screen.ScreenAnalytic

sealed class CommonAnalyticEvent(
    screenAnalytic: ScreenAnalytic
) : AnalyticEvent, ScreenAnalytic by screenAnalytic {
    data class OpenScreen(
        private val screenAnalytic: ScreenAnalytic
    ) : CommonAnalyticEvent(screenAnalytic) {
        override val tag = "screen_view"
        override val params: Map<String, Any> = emptyMap()
    }
}