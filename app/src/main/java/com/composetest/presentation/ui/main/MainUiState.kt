package com.composetest.presentation.ui.main

import android.os.Build
import com.composetest.core.designsystem.extension.systemBarStyles
import com.composetest.core.designsystem.param.alertdialog.SimpleDialogParam
import com.composetest.core.domain.model.AppThemeModel
import com.composetest.core.router.interfaces.Destination
import com.composetest.core.router.interfaces.NavGraph

internal data class MainUiState(
    val firstDestination: Destination? = null,
    val navGraphs: List<NavGraph> = emptyList(),
    val appTheme: AppThemeModel = AppThemeModel(),
    val showSplashScreen: Boolean = true,
    val simpleDialogParam: SimpleDialogParam? = null,
) {
    val statusBarStyle get() = appTheme.systemBarStyles.first
    val navigationBarStyle get() = appTheme.systemBarStyles.second
    val forceNavigationBarTransparency get() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q

    fun setInitUiState(firstDestination: Destination, navGraphs: Array<NavGraph>) =
        copy(
            showSplashScreen = false,
            firstDestination = firstDestination,
            navGraphs = navGraphs.toList()
        )

    fun setAppTheme(appTheme: AppThemeModel?) = copy(appTheme = appTheme ?: this.appTheme)
    fun setSimpleDialog(param: SimpleDialogParam?) = copy(simpleDialogParam = param)
}
