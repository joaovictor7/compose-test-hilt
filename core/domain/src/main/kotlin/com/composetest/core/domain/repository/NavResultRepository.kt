package com.composetest.core.domain.repository

import kotlinx.coroutines.flow.Flow
import kotlin.reflect.KClass

interface NavResultRepository {
    suspend fun <T : Any> setResult(value: T)
    fun <T : Any> observeResult(type: KClass<T>): Flow<T>
}
