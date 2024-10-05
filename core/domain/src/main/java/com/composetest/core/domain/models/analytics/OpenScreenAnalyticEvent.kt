package com.composetest.core.domain.models.analytics

import com.composetest.core.domain.interfaces.analytics.AnalyticEvent
import com.composetest.core.domain.interfaces.analytics.AnalyticScreen

class OpenScreenAnalyticEvent(
    analyticScreen: AnalyticScreen
) : AnalyticEvent, AnalyticScreen by analyticScreen {
    override val tag = "open_screen"
    override val screen = analyticScreen.screen
    override val params = emptyMap<String, Any>()
}