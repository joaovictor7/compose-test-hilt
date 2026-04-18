package com.composetest.core.router.interfaces

import androidx.navigation3.runtime.EntryProviderScope
import androidx.navigation3.runtime.NavKey

interface NavGraph {
    fun EntryProviderScope<NavKey>.registerEntries()
}