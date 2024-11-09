package com.composetest.core.domain.interfaces

interface AnalyticEvent : AnalyticScreen {
    val tag: String
    val params: Map<String, *>
}