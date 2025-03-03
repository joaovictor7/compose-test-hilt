package com.composetest.core.ui.providers

import androidx.annotation.StringRes

interface StringResourceProvider {
    fun getString(@StringRes stringId: Int, vararg args: Any): String
}