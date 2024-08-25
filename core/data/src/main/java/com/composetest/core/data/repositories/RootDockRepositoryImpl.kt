package com.composetest.core.data.repositories

import com.composetest.core.domain.repositories.RootDockRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class RootDockRepositoryImpl @Inject constructor() : RootDockRepository {

    private val _dockVisibleState = MutableStateFlow(true)
    override val dockVisibilityFlow = _dockVisibleState.asStateFlow()

    override fun setVisible(visible: Boolean) {
        _dockVisibleState.update { visible }
    }
}