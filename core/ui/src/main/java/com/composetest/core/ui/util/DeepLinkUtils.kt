package com.composetest.core.ui.util

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.navDeepLink
import com.composetest.core.router.extension.backStackBundle

fun transformDeepLinks(vararg uri: String) = uri.map {
    navDeepLink {
        uriPattern = it
    }
}

@Composable
fun <T> rememberDeepLinkParam(navHost: NavHostController, onMapper: (Bundle?) -> T?) = remember {
    onMapper(navHost.backStackBundle)
}