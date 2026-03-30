package com.composetest.core.domain.usecase.navigation

import com.composetest.core.domain.repository.NavResultRepository
import javax.inject.Inject

class SetNavResultUseCase @Inject constructor(
    private val repository: NavResultRepository
) {
    suspend operator fun <T : Any> invoke(value: T) = repository.setResult(value)
}
