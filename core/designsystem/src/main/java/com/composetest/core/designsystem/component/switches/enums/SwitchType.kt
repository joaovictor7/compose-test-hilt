package com.composetest.core.designsystem.component.switches.enums

import androidx.annotation.DrawableRes
import com.composetest.core.designsystem.R

enum class SwitchType(
    @param:DrawableRes internal val checkedThumb: Int,
    @param:DrawableRes internal val uncheckedThumb: Int
) {
    CHECK(R.drawable.ic_check, R.drawable.ic_close)
}