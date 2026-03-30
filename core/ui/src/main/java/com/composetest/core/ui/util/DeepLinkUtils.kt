package com.composetest.core.ui.util

import androidx.navigation.navDeepLink

fun transformDeepLinks(vararg uri: String) = uri.map {
    navDeepLink {
        uriPattern = it
    }
}

//@Composable
//fun <T> rememberDeepLinkParam(navHost: Nav, onMapper: (Bundle?) -> T?) = remember {
//    onMapper(navHost.backStackBundle)
//}