package com.composetest.feature.login.presenter.model

import androidx.annotation.StringRes

internal data class BiometricModel(
    @StringRes val messageId: Int? = null,
    val isError: Boolean = false,
    val isAvailable: Boolean = true
)
