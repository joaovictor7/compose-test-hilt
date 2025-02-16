package com.composetest.ui

import android.os.Build
import com.composetest.core.designsystem.extensions.systemBarStyles
import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam
import com.composetest.core.domain.models.ThemeModel
import com.composetest.core.router.interfaces.Destination

internal data class MainUiState(
    val firstDestination: Destination? = null,
    val theme: ThemeModel = ThemeModel(),
    val showSplashScreen: Boolean = true,
    val simpleDialogParam: SimpleDialogParam? = null
) {
    val statusBarStyle get() = theme.systemBarStyles.first
    val navigationBarStyle get() = theme.systemBarStyles.second
    val forceNavigationBarTransparency get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    fun setInitState(firstDestination: Destination) =
        copy(showSplashScreen = false, firstDestination = firstDestination)

    fun setTheme(theme: ThemeModel?) = copy(theme = theme ?: this.theme)
    fun setSimpleDialog(param: SimpleDialogParam?) = copy(simpleDialogParam = param)
}
