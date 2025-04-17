package com.composetest.ui.dialogs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.composetest.core.designsystem.components.dialogs.SimpleDialog
import com.composetest.core.router.extensions.navigateBack

@Composable
internal fun NetworkErrorDialog(navHostController: NavHostController) {
    SimpleDialog(
        param = MainSimpleDialogParam.NetworkError,
        onDismiss = { navHostController.navigateBack() }
    )
}