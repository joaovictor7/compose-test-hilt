package com.composetest.core.data.repositories

import com.composetest.core.domain.repositories.RootDockRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class RootDockRepositoryImpl @Inject constructor() : RootDockRepository {

    private val _dockHeightFlow = MutableStateFlow(0)
    override val dockHeightFlow = _dockHeightFlow.asStateFlow()

    override fun setHeight(height: Int) {
        _dockHeightFlow.update { height }
    }
}