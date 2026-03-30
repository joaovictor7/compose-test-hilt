package com.composetest.core.data.impl.repository

import com.composetest.core.data.impl.datasource.NavResultDataSource
import com.composetest.core.domain.repository.NavResultRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlin.reflect.KClass

internal class NavResultRepositoryImpl @Inject constructor(
    private val dataSource: NavResultDataSource
) : NavResultRepository {

    override suspend fun <T : Any> setResult(value: T) = dataSource.set(value)

    override fun <T : Any> observeResult(type: KClass<T>): Flow<T> = dataSource.observe(type)
}
