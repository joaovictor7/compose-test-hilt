package com.composetest.core.domain.repositories

import kotlinx.coroutines.flow.Flow

interface RootDockRepository {
    val dockHeightFlow: Flow<Int>

    fun setHeight(height: Int)
}