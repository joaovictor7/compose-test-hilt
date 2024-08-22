package com.composetest.core.designsystem.components.dock.params

import androidx.annotation.DrawableRes

data class IconDockParam(
    val index: Int,
    @DrawableRes val iconId: Int,
    val contentDescription: String? = null
)
