package com.composetest.feature.configuration.ui.theme

import com.composetest.core.domain.managers.AppThemeManager
import com.composetest.core.domain.usecases.SendAnalyticsUseCase
import com.composetest.core.ui.bases.BaseViewModel
import com.composetest.feature.configuration.enums.ThemeConfiguration
import com.composetest.feature.configuration.ui.theme.analytics.ChangeDynamicColorsEvent
import com.composetest.feature.configuration.ui.theme.analytics.ChangeThemeEvent
import com.composetest.feature.configuration.ui.theme.analytics.ConfigurationThemeAnalytic
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
internal class ConfigurationThemeViewModel @Inject constructor(
    private val appThemeManager: AppThemeManager,
    override val sendAnalyticsUseCase: SendAnalyticsUseCase
) : BaseViewModel<ConfigurationThemeUiState>(
    ConfigurationThemeAnalytic,
    ConfigurationThemeUiState()
), ConfigurationThemeCommandReceiver {

    override val commandReceiver = this

    init {
        initState()
    }

    override fun changeTheme(selectedTheme: ThemeConfiguration) {
        updateUiState { it.setSelectedTheme(selectedTheme) }
        runAsyncTask(
            onStart = { sendAnalyticsUseCase(ChangeThemeEvent(selectedTheme.theme.name)) }
        ) {
            appThemeManager.setTheme(selectedTheme.theme)
        }
    }

    override fun changeDynamicColor(active: Boolean) {
        updateUiState { it.setDynamicColors(active) }
        runAsyncTask(onStart = { sendAnalyticsUseCase(ChangeDynamicColorsEvent(active)) }) {
            appThemeManager.setDynamicColor(active)
        }
    }

    private fun initState() {
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
}