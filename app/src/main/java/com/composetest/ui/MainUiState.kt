package com.composetest.ui

import android.os.Build
import com.composetest.core.designsystem.extensions.systemBarStyles
import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam
import com.composetest.core.domain.models.AppThemeModel
import com.composetest.core.router.interfaces.Destination

internal data class MainUiState(
    val firstDestination: Destination? = null,
    val appTheme: AppThemeModel = AppThemeModel(),
    val showSplashScreen: Boolean = true,
    val simpleDialogParam: SimpleDialogParam? = null
) {
    val statusBarStyle get() = appTheme.systemBarStyles.first
    val navigationBarStyle get() = appTheme.systemBarStyles.second
    val forceNavigationBarTransparency get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    fun setInitState(firstDestination: Destination) =
        copy(showSplashScreen = false, firstDestination = firstDestination)

    fun setAppTheme(appTheme: AppThemeModel?) = copy(appTheme = appTheme ?: this.appTheme)
    fun setSimpleDialog(param: SimpleDialogParam?) = copy(simpleDialogParam = param)
}
