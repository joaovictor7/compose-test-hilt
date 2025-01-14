package com.composetest.common.analytics

interface AnalyticEvent : AnalyticScreen {
    val tag: String
    val params: Map<String, *>
}