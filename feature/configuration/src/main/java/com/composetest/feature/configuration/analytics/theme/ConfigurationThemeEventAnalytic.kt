package com.composetest.feature.configuration.analytics.theme

import com.composetest.core.analytic.AnalyticEvent
import com.composetest.core.analytic.AnalyticScreen

sealed class ConfigurationThemeEventAnalytic : AnalyticEvent,
    AnalyticScreen by ConfigurationThemeScreenAnalytic {

    data class ChangeTheme(
        private val theme: String? = null,
        private val dynamicColor: Boolean? = null
    ) : ConfigurationThemeEventAnalytic() {
        override val tag = "change_theme"
        override val params: Map<String, Any> = mutableMapOf<String, Any>().apply {
            theme?.let {
                this["theme"] = it
            }
            dynamicColor?.let {
                this["dynamic_colors"] = it
            }
        }
    }
}