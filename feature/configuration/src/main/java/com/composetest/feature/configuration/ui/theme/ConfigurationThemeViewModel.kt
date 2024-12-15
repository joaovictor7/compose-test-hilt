package com.composetest.feature.configuration.ui.theme

import com.composetest.core.domain.enums.Theme
import com.composetest.core.domain.managers.AppThemeManager
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
    private val appThemeManager: AppThemeManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase,
    @NavGraphQualifier(NavGraph.MAIN) override val navigationManager: NavigationManager
) : BaseViewModel<ConfigurationThemeUiState, ConfigurationThemeUiEvent>(
    ConfigurationThemeScreenAnalytic,
    ConfigurationThemeUiState()
), ConfigurationThemeCommandReceiver {

    override val commandReceiver = this

    override fun initUiState() {
        runAsyncTask {
            appThemeManager.getAppTheme()?.let { appTheme ->
                updateUiState {
                    it.initUiState(
                        ThemeConfiguration.entries,
                        ThemeConfiguration.getThemeConfiguration(appTheme.theme),
                        appTheme.dynamicColors
                    )
                }
            }
        }
    }

    override fun changeTheme(selectedTheme: ThemeConfiguration) {
        updateUiState { it.setSelectedTheme(selectedTheme) }
        runAsyncTask {
            sendChangeThemeAnalytic(theme = selectedTheme.theme)
            appThemeManager.setTheme(selectedTheme.theme)
        }
    }

    override fun changeDynamicColor(active: Boolean) {
        updateUiState { it.setDynamicColors(active) }
        runAsyncTask {
            sendChangeThemeAnalytic(dynamicColors = active)
            appThemeManager.setDynamicColor(active)
        }
    }

    private suspend fun sendChangeThemeAnalytic(dynamicColors: Boolean? = null, theme: Theme? = null) {
        sendAnalyticsUseCase(ConfigurationThemeEventAnalytic.ChangeTheme(theme?.name, dynamicColors))
    }
}