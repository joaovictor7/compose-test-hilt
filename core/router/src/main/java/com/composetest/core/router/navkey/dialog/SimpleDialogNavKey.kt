package com.composetest.core.router.navkey.dialog

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.navigation3.runtime.NavKey
import kotlinx.serialization.Serializable

@Serializable
data class SimpleDialogNavKey(
    @get:DrawableRes val iconId: Int,
    @get:StringRes val titleId: Int,
    @get:StringRes val textId: Int,
    @get:StringRes val dismissButtonTextId: Int? = null,
    @get:StringRes val confirmButtonTextId: Int? = null,
) : NavKey