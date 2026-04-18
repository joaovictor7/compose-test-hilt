package com.composetest.core.router.navstack

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey

val LocalMainNavBackStack = staticCompositionLocalOf<NavBackStack<NavKey>> {
    error("LocalMainNavBackStack not provided")
}
