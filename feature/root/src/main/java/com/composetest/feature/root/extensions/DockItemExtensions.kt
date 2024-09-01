package com.composetest.feature.root.extensions

import com.composetest.core.designsystem.R
import com.composetest.core.domain.enums.DockItem

internal val DockItem.iconId
    get() = when (this) {
        DockItem.HOME -> R.drawable.ic_house_filled
        DockItem.CONFIGURATION -> R.drawable.ic_config_filled
        DockItem.HOME2 -> R.drawable.ic_config_filled
    }