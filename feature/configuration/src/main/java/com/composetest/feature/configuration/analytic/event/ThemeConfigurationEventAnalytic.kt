package com.composetest.feature.configuration.analytic.event

import com.composetest.core.analytic.event.AnalyticEvent
import com.composetest.core.analytic.screen.ScreenAnalytic
import com.composetest.feature.configuration.analytic.screen.ThemeConfigurationScreenAnalytic

internal sealed class ThemeConfigurationEventAnalytic : AnalyticEvent,
    ScreenAnalytic by ThemeConfigurationScreenAnalytic {

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