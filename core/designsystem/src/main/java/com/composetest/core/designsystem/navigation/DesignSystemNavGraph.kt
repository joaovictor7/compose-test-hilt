package com.composetest.core.designsystem.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.dialog
import com.composetest.core.designsystem.component.dialog.SimpleDialog
import com.composetest.core.designsystem.param.dialog.GenericErrorParam
import com.composetest.core.designsystem.param.dialog.NetworkErrorParam
import com.composetest.core.router.destination.dialog.GenericErrorDialog
import com.composetest.core.router.destination.dialog.NetworkErrorDialog
import com.composetest.core.router.extension.navigateBack
import com.composetest.core.router.interfaces.NavGraph

object DesignSystemNavGraph : NavGraph {
    override fun NavGraphBuilder.register(
        navController: NavHostController
    ) {
        dialog<GenericErrorDialog> {
            SimpleDialog(GenericErrorParam) {
                navController.navigateBack()
            }
        }
        dialog<NetworkErrorDialog> {
            SimpleDialog(NetworkErrorParam) {
                navController.navigateBack()
            }
        }
    }
}