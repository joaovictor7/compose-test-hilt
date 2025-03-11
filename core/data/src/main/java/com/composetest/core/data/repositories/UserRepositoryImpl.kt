package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.local.UserDataSource
import com.composetest.core.data.mappers.UserMapper
import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.repositories.ConfigurationRepository
import com.composetest.core.domain.repositories.UserRepository
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    private val configurationRepository: ConfigurationRepository,
    private val userMapper: UserMapper,
) : UserRepository {

    override suspend fun upsert(user: UserModel) {
        userDataSource.upsert(userMapper.mapperToEntity(user))
        configurationRepository.insertDefaultUserConfiguration(user.id)
    }

    override suspend fun getCurrentUser() =
        userMapper.mapperToModel(userDataSource.getCurrentUser())

    override suspend fun getLastActiveUser() =
        userMapper.mapperToModel(userDataSource.getLastActiveUser())
}