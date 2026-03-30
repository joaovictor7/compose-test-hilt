package com.composetest.core.designsystem.navigation

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavBackStack
import androidx.navigation3.runtime.NavKey
import com.composetest.core.designsystem.component.dialog.SimpleDialog
import com.composetest.core.router.extension.navigateBack
import com.composetest.core.router.interfaces.NavGraph
import com.composetest.core.router.navkey.dialog.SimpleDialogNavKey
import javax.inject.Inject

internal class NavGraphImpl @Inject constructor() : NavGraph {
    override fun EntryProviderScope<NavKey>.registerEntries(navBackStack: NavBackStack<NavKey>) {
        entry<SimpleDialogNavKey> { dest ->
            SimpleDialog(dest) {
                navBackStack.navigateBack()
            }
        }
    }
}
