package com.composetest.core.designsystem.utils

import com.composetest.core.designsystem.components.dialogs.CommonSimpleDialog
import com.composetest.core.domain.errors.ApiError

fun getCommonSimpleDialogErrorParam(error: Throwable?) = error?.let {
    when (it) {
        is ApiError.Network -> CommonSimpleDialog.NetworkError
        else -> CommonSimpleDialog.GenericError
    }
}