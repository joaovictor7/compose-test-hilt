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
        userDataSource.upsert(userMapper.mapperToEntity(user))
        configurationDataSource.upsert(configurationMapper.mapperToEntity(ConfigurationModel(userId = user.id)))
    }

    override suspend fun getCurrentUser() =
        userMapper.mapperToModel(userDataSource.getCurrentUser())

    override suspend fun getLastActiveUser() =
        userMapper.mapperToModel(userDataSource.getLastActiveUser())
}