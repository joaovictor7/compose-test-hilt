package com.composetest.ui

import com.composetest.core.designsystem.components.alertdialogs.params.DefaultAlertDialogParam
import com.composetest.core.domain.models.AppThemeModel
import com.composetest.core.ui.interfaces.BaseUiState

data class MainUiState(
    val appTheme: AppThemeModel = AppThemeModel(),
    val showSplashScreen: Boolean = true,
    val defaultAlertDialogParam: DefaultAlertDialogParam? = null
) : BaseUiState {
    val statusBarStyle get() = appTheme.systemBarStyles.first
    val navigationBarStyle get() = appTheme.systemBarStyles.second

    fun setAppTheme(appTheme: AppThemeModel) = copy(appTheme = appTheme)

    fun splashScreenFinished() = copy(showSplashScreen = false)

    fun setDefaultAlertDialogParam(defaultAlertDialogParam: DefaultAlertDialogParam?) = copy(
        defaultAlertDialogParam = defaultAlertDialogParam
    )
}
