package com.composetest.presentation.ui.dialogs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.composetest.core.designsystem.components.dialogs.SimpleDialog
import com.composetest.core.router.extensions.navigateBack

@Composable
internal fun GenericErrorDialog(navHostController: NavHostController) {
    SimpleDialog(
        param = MainSimpleDialogParam.GenericError,
        onDismiss = { navHostController.navigateBack() }
    )
}