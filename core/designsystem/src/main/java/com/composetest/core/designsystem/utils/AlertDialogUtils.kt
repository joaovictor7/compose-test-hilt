package com.composetest.core.designsystem.utils

import com.composetest.core.designsystem.components.dialogs.CommonSimpleDialog
import com.composetest.common.errors.ApiError
import com.composetest.core.designsystem.params.alertdialogs.SimpleDialogParam

fun getCommonSimpleDialogErrorParam(
    error: Throwable?,
    defaultError: SimpleDialogParam? = CommonSimpleDialog.GenericError
) = error?.let {
    when (it) {
        is ApiError.Network -> CommonSimpleDialog.NetworkError
        else -> defaultError
    }
}