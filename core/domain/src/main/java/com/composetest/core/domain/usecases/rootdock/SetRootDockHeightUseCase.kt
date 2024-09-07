package com.composetest.core.domain.usecases.rootdock

import com.composetest.core.domain.repositories.RootDockRepository
import javax.inject.Inject

class SetRootDockHeightUseCase @Inject constructor(
    private val rootDockRepository: RootDockRepository
) {
    operator fun invoke(height: Int) {
        rootDockRepository.setHeight(height)
    }
}