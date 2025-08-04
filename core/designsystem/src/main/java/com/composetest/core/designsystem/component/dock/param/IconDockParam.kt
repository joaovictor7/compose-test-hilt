package com.composetest.core.designsystem.component.dock.param

import androidx.annotation.DrawableRes

data class IconDockParam(
    val index: Int,
    @param:DrawableRes val iconId: Int,
    val contentDescription: String? = null
)