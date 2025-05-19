package com.composetest.core.designsystem.param.alertdialog

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

interface SimpleDialogParam {
    @get:DrawableRes
    val iconId: Int

    @get:StringRes
    val titleId: Int

    @get:StringRes
    val textId: Int

    @get:StringRes
    val dismissButtonTextId: Int? get() = null

    @get:StringRes
    val confirmButtonTextId: Int? get() = null
}