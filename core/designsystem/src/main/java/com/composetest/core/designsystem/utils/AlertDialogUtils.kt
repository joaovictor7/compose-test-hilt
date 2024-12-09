package com.composetest.core.designsystem.utils

import com.composetest.core.designsystem.params.alertdialogs.DefaultAlertDialogParam
import com.composetest.core.domain.errors.ApiError

fun getErrorAlertDialogParam(
    throwable: Throwable?,
    onDismiss: () -> Unit
) = throwable?.let {
    when (it) {
        is ApiError.Network -> DefaultAlertDialogParam.getNetworkAlertDialogParam(onDismiss)
        else -> DefaultAlertDialogParam.getGenericAlertDialogParam(onDismiss)
    }
}