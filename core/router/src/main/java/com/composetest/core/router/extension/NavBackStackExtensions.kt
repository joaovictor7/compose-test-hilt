package com.composetest.core.router.extension

import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.composetest.core.router.enums.NavigationMode
import com.composetest.core.router.model.NavigationModel

val NavBackStack<NavKey>.currentNavKey: NavKey? get() = lastOrNull()

fun NavBackStack<NavKey>.navigateTo(navigationModel: NavigationModel) {
    when (navigationModel.navigationMode) {
        NavigationMode.REMOVE_CURRENT_SCREEN -> {
            removeLastOrNull()
            add(navigationModel.navKey)
        }
        NavigationMode.REMOVE_ALL_SCREENS_STACK -> {
            clear()
            add(navigationModel.navKey)
        }
        NavigationMode.SAVE_SCREEN_STATE -> {
            remove(navigationModel.navKey)
            add(navigationModel.navKey)
        }
        null -> add(navigationModel.navKey)
    }
}

fun NavBackStack<NavKey>.navigateBack() {
    removeLastOrNull()
}
