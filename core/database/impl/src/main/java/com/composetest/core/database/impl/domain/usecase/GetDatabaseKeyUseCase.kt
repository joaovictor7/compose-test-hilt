package com.composetest.core.database.impl.domain.usecase

import com.composetest.core.database.impl.data.repository.DatabaseRepository
import com.composetest.core.domain.provider.BuildConfigProvider
import com.composetest.core.security.api.provider.CipherProvider
import javax.inject.Inject

internal class GetDatabaseKeyUseCase @Inject constructor(
    private val cipherProvider: CipherProvider,
    private val buildConfigProvider: BuildConfigProvider,
    private val databaseRepository: DatabaseRepository,
    private val createDatabaseKeyUseCase: CreateDatabaseKeyUseCase,
) {

    suspend operator fun invoke() = if (buildConfigProvider.buildConfig.isRelease) {
        val key = databaseRepository.getSqliteSecretKey()?.let {
            cipherProvider.decrypt(it)
        } ?: createDatabaseKeyUseCase()
        key.toByteArray()
    } else null
}