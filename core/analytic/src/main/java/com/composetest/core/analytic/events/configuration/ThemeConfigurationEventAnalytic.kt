package com.composetest.core.analytic.events.configuration

import com.composetest.core.analytic.AnalyticEvent
import com.composetest.core.analytic.AnalyticScreen

sealed class ThemeConfigurationEventAnalytic : AnalyticEvent,
    AnalyticScreen by ThemeConfigurationScreenAnalytic {

    data class ChangeThemeConfiguration(
        private val theme: String? = null,
        private val dynamicColor: Boolean? = null
    ) : ThemeConfigurationEventAnalytic() {
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