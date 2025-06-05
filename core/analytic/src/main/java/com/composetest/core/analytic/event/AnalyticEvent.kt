package com.composetest.core.analytic.event

import com.composetest.core.analytic.screen.ScreenAnalytic

interface AnalyticEvent : ScreenAnalytic {
    val tag: String
    val params: Map<String, *>
}