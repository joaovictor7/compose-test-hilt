package com.composetest.core.domain.usecases.rootdock

import com.composetest.core.domain.repositories.RootDockRepository
import javax.inject.Inject

class GetRootDockHeightUseCase @Inject constructor(
    private val rootDockRepository: RootDockRepository
) {
    operator fun invoke() = rootDockRepository.dockHeightFlow
}