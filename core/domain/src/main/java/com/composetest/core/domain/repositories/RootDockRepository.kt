package com.composetest.core.domain.repositories

import kotlinx.coroutines.flow.Flow

interface RootDockRepository {
    val dockVisibilityFlow: Flow<Boolean>

    fun setVisible(visible: Boolean)
}