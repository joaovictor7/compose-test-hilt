package com.composetest.core.domain.analytics

interface AnalyticEvent : AnalyticScreen {
    val tag: String
    val params: Map<String, *>
}