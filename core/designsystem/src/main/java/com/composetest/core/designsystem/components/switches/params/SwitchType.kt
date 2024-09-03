package com.composetest.core.designsystem.components.switches.params

import androidx.annotation.DrawableRes
import com.composetest.core.designsystem.R

enum class SwitchType(
    @DrawableRes internal val checkedThumb: Int,
    @DrawableRes internal val uncheckedThumb: Int
) {
    CHECK(R.drawable.ic_check, R.drawable.ic_close)
}