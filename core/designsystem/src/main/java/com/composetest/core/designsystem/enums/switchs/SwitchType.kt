package com.composetest.core.designsystem.enums.switchs

import androidx.annotation.DrawableRes
import com.composetest.core.designsystem.R

enum class SwitchType(
    @param:DrawableRes internal val checkedThumb: Int,
    @param:DrawableRes internal val uncheckedThumb: Int
) {
    CHECK(R.drawable.ic_check, R.drawable.ic_close)
}