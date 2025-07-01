package com.composetest.core.router.destination.dialog

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.composetest.core.router.interfaces.Destination
import kotlinx.serialization.Serializable

@Serializable
data class SimpleDialogDestination(
    @get:DrawableRes val iconId: Int,
    @get:StringRes val titleId: Int,
    @get:StringRes val textId: Int,
    @get:StringRes val dismissButtonTextId: Int? = null,
    @get:StringRes val confirmButtonTextId: Int? = null,
) : Destination