package com.composetest.feature.root.analytic.events

import com.composetest.core.analytic.AnalyticEvent
import com.composetest.core.analytic.ScreenAnalytic
import com.composetest.feature.root.analytic.screens.RootScreenAnalytic

sealed class RootEventAnalytic : AnalyticEvent, ScreenAnalytic by RootScreenAnalytic {
    data class NavigateToFeature(private val feature: String) : RootEventAnalytic() {
        override val tag = "navigate_to_feature"
        override val params: Map<String, Any> = mapOf("feature" to feature.lowercase())
    }
}