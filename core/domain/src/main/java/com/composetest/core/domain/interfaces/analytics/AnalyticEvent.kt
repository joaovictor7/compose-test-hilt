package com.composetest.core.domain.interfaces.analytics

interface AnalyticEvent : AnalyticScreen {
    val tag: String
    val params: Map<String, *>
}