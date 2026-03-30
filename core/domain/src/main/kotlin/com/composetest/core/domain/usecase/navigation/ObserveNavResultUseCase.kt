package com.composetest.core.domain.usecase.navigation

import com.composetest.core.domain.repository.NavResultRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.reflect.KClass

class ObserveNavResultUseCase @Inject constructor(
    private val repository: NavResultRepository
) {
    operator fun <T : Any> invoke(type: KClass<T>): Flow<T> = repository.observeResult(type)
}
