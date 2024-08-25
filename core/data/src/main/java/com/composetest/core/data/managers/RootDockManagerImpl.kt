package com.composetest.core.data.managers

import com.composetest.core.domain.managers.RootDockManager
import com.composetest.core.domain.repositories.RootDockRepository
import javax.inject.Inject

internal class RootDockManagerImpl @Inject constructor(
    private val rootDockRepository: RootDockRepository
) : RootDockManager {
    override val dockVisibilityFlow = rootDockRepository.dockVisibilityFlow

    override fun setRootDockVisibility(visible: Boolean) {
        rootDockRepository.setVisible(visible)
    }
}