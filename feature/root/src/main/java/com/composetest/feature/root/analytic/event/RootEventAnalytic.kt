package com.composetest.feature.root.analytic.event

import com.composetest.core.analytic.event.AnalyticEvent
import com.composetest.core.analytic.screen.ScreenAnalytic
import com.composetest.feature.root.analytic.screen.RootScreenAnalytic

internal sealed class RootEventAnalytic : AnalyticEvent, ScreenAnalytic by RootScreenAnalytic {
    data class NavigateToFeature(private val featureName: String) : RootEventAnalytic() {
        override val tag = "navigate_to_feature"
        override val params: Map<String, Any> = mapOf("feature_name" to featureName.lowercase())
    }
}