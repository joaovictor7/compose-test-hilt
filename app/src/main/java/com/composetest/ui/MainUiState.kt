package com.composetest.ui

import android.os.Build
import com.composetest.core.designsystem.extensions.systemBarStyles
import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam
import com.composetest.core.domain.models.ThemeModel
import com.composetest.core.ui.interfaces.BaseUiState

data class MainUiState(
    val appTheme: ThemeModel = ThemeModel(),
    val showSplashScreen: Boolean = true,
    val simpleDialogParam: SimpleDialogParam? = null
) : BaseUiState {
    val statusBarStyle get() = appTheme.systemBarStyles.first
    val navigationBarStyle get() = appTheme.systemBarStyles.second
    val forceNavigationBarTransparency get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    fun setTheme(appTheme: ThemeModel?) = copy(setTheme = appTheme ?: this.appTheme)
    fun splashScreenFinished() = copy(showSplashScreen = false)
    fun setSimpleDialog(param: SimpleDialogParam?) = copy(simpleDialogParam = param)
}
