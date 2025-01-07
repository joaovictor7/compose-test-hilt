package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.local.ConfigurationDataSource
import com.composetest.core.data.datasources.local.UserDataSource
import com.composetest.core.data.mappers.ConfigurationMapper
import com.composetest.core.data.mappers.UserMapper
import com.composetest.core.domain.models.ConfigurationModel
import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.repositories.UserRepository
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    private val configurationDataSource: ConfigurationDataSource,
    private val userMapper: UserMapper,
    private val configurationMapper: ConfigurationMapper,
) : UserRepository {

    override suspend fun upsert(user: UserModel) {
        userDataSource.upsert(userMapper(user))
        configurationDataSource.upsert(configurationMapper(ConfigurationModel(userId = user.id)))
    }

    override suspend fun getCurrentUser() =
        userMapper(userDataSource.getCurrentUser())

    override suspend fun getLastUser() = userMapper(userDataSource.getLastUser())
}