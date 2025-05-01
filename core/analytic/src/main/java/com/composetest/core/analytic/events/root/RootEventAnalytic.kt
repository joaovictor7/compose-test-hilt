package com.composetest.core.analytic.events.root

import com.composetest.core.analytic.AnalyticEvent
import com.composetest.core.analytic.AnalyticScreen
import com.composetest.core.analytic.enums.ScreensAnalytic

sealed class RootEventAnalytic : AnalyticEvent, AnalyticScreen by ScreensAnalytic.ROOT {
    data class NavigateToFeature(private val feature: String) : RootEventAnalytic() {
        override val tag = "navigate_to_feature"
        override val params: Map<String, Any> = mapOf("feature" to feature.lowercase())
    }
}