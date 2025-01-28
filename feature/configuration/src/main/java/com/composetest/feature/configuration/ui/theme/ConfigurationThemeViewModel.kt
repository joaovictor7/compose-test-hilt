package com.composetest.feature.configuration.ui.theme

import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.managers.ConfigurationManager
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.router.di.qualifiers.NavGraphQualifier
import com.composetest.core.router.enums.NavGraph
import com.composetest.core.router.managers.NavigationManager
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.configuration.analytics.theme.ConfigurationThemeEventAnalytic
import com.composetest.feature.configuration.analytics.theme.ConfigurationThemeScreenAnalytic
import com.composetest.feature.configuration.enums.ThemeConfiguration
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ConfigurationThemeViewModel @Inject constructor(
    private val configurationManager: ConfigurationManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
    @NavGraphQualifier(NavGraph.MAIN) override val navigationManager: NavigationManager
) : BaseViewModel<ConfigurationThemeUiState, ConfigurationThemeUiEvent>(
    ConfigurationThemeUiState()
), ConfigurationThemeCommandReceiver {

    override val commandReceiver = this
    override val analyticScreen = ConfigurationThemeScreenAnalytic

    init {
        initUiState()
    }

    override fun changeTheme(selectedTheme: ThemeConfiguration) {
        updateUiState { it.setSelectedTheme(selectedTheme) }
        runAsyncTask {
            sendChangeThemeAnalytic(theme = selectedTheme.theme)
            configurationManager.setTheme(selectedTheme.theme)
        }
    }

    override fun changeDynamicColor(active: Boolean) {
        updateUiState { it.setDynamicColors(active) }
        runAsyncTask {
            sendChangeThemeAnalytic(dynamicColor = active)
            configurationManager.setDynamicColors(active)
        }
    }

    private fun initUiState() {
        runAsyncTask {
            configurationManager.fetchConfiguration()
            val currentTheme = configurationManager.getCurrentTheme()
            updateUiState {
                it.initUiState(
                    ThemeConfiguration.entries,
                    ThemeConfiguration.getThemeConfiguration(currentTheme.theme),
                    currentTheme.dynamicColor
                )
            }
        }
    }

    private suspend fun sendChangeThemeAnalytic(
        dynamicColor: Boolean? = null,
        theme: Theme? = null
    ) {
        sendAnalyticsUseCase(
            ConfigurationThemeEventAnalytic.ChangeTheme(theme?.name, dynamicColor)
        )
    }
}