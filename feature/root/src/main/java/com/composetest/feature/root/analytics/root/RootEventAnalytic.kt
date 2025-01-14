package com.composetest.feature.root.analytics.root

import com.composetest.core.domain.enums.Feature

internal sealed class RootEventAnalytic : com.composetest.common.analytics.AnalyticEvent, com.composetest.common.analytics.AnalyticScreen by RootScreenAnalytic {
    data class NavigateToFeature(private val feature: Feature) : RootEventAnalytic() {
        override val tag = "navigate_to_feature"
        override val params: Map<String, Any> = mapOf(
            "feature" to feature.name
        )
    }
}