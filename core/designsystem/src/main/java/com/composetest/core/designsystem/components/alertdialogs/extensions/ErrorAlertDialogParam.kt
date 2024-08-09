package com.composetest.core.designsystem.components.alertdialogs.extensions

import com.composetest.core.designsystem.components.alertdialogs.params.ErrorAlertDialogParam
import com.composetest.core.domain.throwables.network.NetworkThrowable

val Throwable?.errorAlertDialogParam
    get() = this?.let {
        when (it) {
            is NetworkThrowable -> ErrorAlertDialogParam.networkErrorAlertDialogParam
            else -> ErrorAlertDialogParam.genericErrorAlertDialogParam
        }
    }