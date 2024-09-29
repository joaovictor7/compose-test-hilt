package com.composetest.common.analytics

import com.composetest.common.analytics.interfaces.AnalyticEvent
import com.composetest.common.analytics.interfaces.AnalyticScreen

class OpenScreenAnalyticEvent(
    analyticScreen: AnalyticScreen
) : AnalyticEvent, AnalyticScreen by analyticScreen {
    override val tag = "open_screen"
    override val screen = analyticScreen.screen
    override val params = emptyMap<String, Any>()
}