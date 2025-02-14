package com.composetest.core.router.models

import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.interfaces.Destination

data class NavigationModel(
    val destination: Destination,
    val navigationMode: NavigationMode? = null
)
