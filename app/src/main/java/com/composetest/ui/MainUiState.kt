package com.composetest.ui

import android.os.Build
import com.composetest.core.designsystem.extensions.systemBarStyles
import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam
import com.composetest.core.domain.models.ThemeModel

internal data class MainUiState(
    val theme: ThemeModel = ThemeModel(),
    val showSplashScreen: Boolean = true,
    val simpleDialogParam: SimpleDialogParam? = null
) {
    val statusBarStyle get() = theme.systemBarStyles.first
    val navigationBarStyle get() = theme.systemBarStyles.second
    val forceNavigationBarTransparency get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    fun setTheme(theme: ThemeModel?) = copy(theme = theme ?: this.theme)
    fun splashScreenFinished() = copy(showSplashScreen = false)
    fun setSimpleDialog(param: SimpleDialogParam?) = copy(simpleDialogParam = param)
}
