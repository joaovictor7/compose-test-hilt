package com.composetest.core.ui.provider

import androidx.annotation.StringRes

interface StringResourceProvider {
    fun getString(@StringRes stringId: Int, vararg args: Any): String
}