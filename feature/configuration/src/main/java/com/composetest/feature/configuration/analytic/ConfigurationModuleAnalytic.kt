package com.composetest.feature.configuration.analytic

import com.composetest.core.analytic.api.screen.ScreenAnalytic

internal abstract class ConfigurationModuleAnalytic : ScreenAnalytic {
    final override val feature = "configuration"
}