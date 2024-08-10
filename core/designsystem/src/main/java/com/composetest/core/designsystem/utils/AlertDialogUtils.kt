package com.composetest.core.designsystem.utils

import com.composetest.core.designsystem.components.alertdialogs.params.DefaultAlertDialogParam
import com.composetest.core.domain.throwables.network.NetworkThrowable
import javax.inject.Inject

class AlertDialogUtils @Inject constructor() {
    fun getErrorAlertDialogParam(
        throwable: Throwable?,
        onDismiss: () -> Unit
    ) = throwable?.let {
        when (it) {
            is NetworkThrowable -> DefaultAlertDialogParam.getNetworkAlertDialogParam(onDismiss)
            else -> DefaultAlertDialogParam.getGenericAlertDialogParam(onDismiss)
        }
    }
}