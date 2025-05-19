package com.composetest.presentation.ui.dialog

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.composetest.core.designsystem.component.dialog.SimpleDialog
import com.composetest.core.router.extension.navigateBack

@Composable
internal fun GenericErrorDialog(navHostController: NavHostController) {
    SimpleDialog(
        param = MainSimpleDialogParam.GenericError,
        onDismiss = { navHostController.navigateBack() }
    )
}