package com.composetest.core.designsystem.utils

import com.composetest.core.designsystem.params.alertdialogs.DefaultSimpleDialogParam
import com.composetest.core.domain.errors.ApiError

fun getDefaultSimpleDialogErrorParam(error: Throwable?) = error?.let {
    when (it) {
        is ApiError.Network -> DefaultSimpleDialogParam.NetworkError
        else -> DefaultSimpleDialogParam.GenericError
    }
}