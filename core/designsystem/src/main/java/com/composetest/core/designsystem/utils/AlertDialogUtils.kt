package com.composetest.core.designsystem.utils

import com.composetest.core.designsystem.params.alertdialogs.DefaultAlertDialogParam
import com.composetest.core.domain.throwables.network.NetworkThrowable

fun getErrorAlertDialogParam(
    throwable: Throwable?,
    onDismiss: () -> Unit
) = throwable?.let {
    when (it) {
        is NetworkThrowable -> DefaultAlertDialogParam.getNetworkAlertDialogParam(onDismiss)
        else -> DefaultAlertDialogParam.getGenericAlertDialogParam(onDismiss)
    }
}