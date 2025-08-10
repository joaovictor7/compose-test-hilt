package com.composetest.feature.login.presentation.model

import androidx.annotation.StringRes

internal data class BiometricModel(
    @param:StringRes val messageId: Int? = null,
    val isError: Boolean = false,
    val isAvailable: Boolean = true
)
