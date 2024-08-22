package com.composetest.feature.root.enums

import androidx.annotation.DrawableRes
import com.composetest.core.designsystem.R

internal enum class DockItem(@DrawableRes val iconId: Int) {
    HOME(R.drawable.ic_house_filled),
    CONFIGURATION(R.drawable.ic_config_filled);

    companion object {
        fun getItemDock(index: Int) = entries[index]
    }
}