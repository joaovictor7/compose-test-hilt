package com.composetest.core.analytic

interface AnalyticEvent : AnalyticScreen {
    val tag: String
    val params: Map<String, *>
}