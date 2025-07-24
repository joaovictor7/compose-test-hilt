package com.composetest.core.analytic.api.event

import com.composetest.core.analytic.api.screen.ScreenAnalytic

interface AnalyticEvent : ScreenAnalytic {
    val tag: String
    val params: Map<String, *>
}