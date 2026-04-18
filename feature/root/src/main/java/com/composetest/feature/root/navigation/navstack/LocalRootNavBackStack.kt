package com.composetest.feature.root.navigation.navstack

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

val LocalRootNavBackStack = staticCompositionLocalOf<NavBackStack<NavKey>> {
    error("LocalRootNavBackStack not provided")
}
