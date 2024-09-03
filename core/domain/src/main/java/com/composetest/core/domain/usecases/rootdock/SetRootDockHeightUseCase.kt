package com.composetest.core.domain.usecases.rootdock

import com.composetest.core.domain.repositories.RootDockRepository
import javax.inject.Inject

class SetRootDockHeightUseCase @Inject constructor(
    private val rootDockRepository: RootDockRepository
) {
    operator fun invoke(height: Int) {
        rootDockRepository.setHeight(height.addExtraPadding())
    }

    private fun Int.addExtraPadding() = ((this * EXTRA_PADDING_PERCENTAGE) + this).toInt()

    private companion object {
        const val EXTRA_PADDING_PERCENTAGE = 0.3
    }
}