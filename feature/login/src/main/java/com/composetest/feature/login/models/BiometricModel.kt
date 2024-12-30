package com.composetest.feature.login.models

import androidx.annotation.StringRes

data class BiometricModel(
    @StringRes val messageId: Int? = null,
    val isError: Boolean = false,
    val isAvailable: Boolean = true
)
