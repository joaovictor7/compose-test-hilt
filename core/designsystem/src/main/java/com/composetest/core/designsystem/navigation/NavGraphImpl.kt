package com.composetest.core.designsystem.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.dialog
import com.composetest.core.designsystem.component.dialog.SimpleDialog
import com.composetest.core.router.destination.dialog.SimpleDialogDestination
import com.composetest.core.router.extension.getDestination
import com.composetest.core.router.extension.navigateBack
import com.composetest.core.router.interfaces.NavGraph
import javax.inject.Inject

internal class NavGraphImpl @Inject constructor() : NavGraph {
    override fun NavGraphBuilder.register(navController: NavHostController) {
        dialog<SimpleDialogDestination> {
            val param = navController.getDestination<SimpleDialogDestination>() ?: return@dialog
            SimpleDialog(param) {
                navController.navigateBack()
            }
        }
    }
}