package com.composetest.core.domain.managers

import kotlinx.coroutines.flow.Flow

interface RootDockManager {
    val dockVisibilityFlow: Flow<Boolean>

    fun setRootDockVisibility(visible: Boolean)
}