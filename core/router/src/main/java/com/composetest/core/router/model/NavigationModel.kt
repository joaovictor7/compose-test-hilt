package com.composetest.core.router.model

import androidx.navigation3.runtime.NavKey
import com.composetest.core.router.enums.NavigationMode

data class NavigationModel(
    val navKey: NavKey,
    val navigationMode: NavigationMode? = null
)
