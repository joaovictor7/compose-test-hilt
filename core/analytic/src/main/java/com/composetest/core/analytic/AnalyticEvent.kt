package com.composetest.core.analytic

interface AnalyticEvent : ScreenAnalytic {
    val tag: String
    val params: Map<String, *>
}