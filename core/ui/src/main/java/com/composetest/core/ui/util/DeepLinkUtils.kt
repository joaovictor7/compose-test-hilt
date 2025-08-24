package com.composetest.core.ui.util

import androidx.navigation.navDeepLink
import com.composetest.core.router.interfaces.Destination

inline fun <reified T : Destination> provideDeepLinks(vararg uri: String) = uri.map {
    navDeepLink<T>(basePath = it)
}