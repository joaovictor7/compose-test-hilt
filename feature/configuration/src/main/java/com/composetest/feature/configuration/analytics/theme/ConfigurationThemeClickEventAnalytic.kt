package com.composetest.feature.configuration.analytics.theme

import com.composetest.core.domain.analytics.AnalyticEvent
import com.composetest.core.domain.analytics.AnalyticScreen

sealed class ConfigurationThemeEventAnalytic :
    AnalyticEvent,
    AnalyticScreen by ConfigurationThemeScreenAnalytic {

    data class ChangeTheme(
        private val theme: String? = null,
        private val dynamicColors: Boolean? = null
    ) : ConfigurationThemeEventAnalytic() {
        override val tag = "change_theme"
        override val params: Map<String, Any> = mutableMapOf<String, Any>().apply {
            theme?.let {
                this["theme"] = it
            }
            dynamicColors?.let {
                this["dynamic_colors"] = it
            }
        }
    }
}