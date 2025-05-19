package com.composetest.feature.account.data.repository

import com.composetest.core.domain.model.UserModel
import com.composetest.core.domain.repository.UserRepository
import com.composetest.feature.account.data.datasource.UserDataSource
import com.composetest.feature.account.data.mapper.UserMapper
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    private val userMapper: UserMapper,
) : UserRepository {

    override suspend fun upsert(user: UserModel) {
        userDataSource.upsert(userMapper.mapperToEntity(user))
    }

    override suspend fun getCurrentUser() =
        userMapper.mapperToModel(userDataSource.getCurrentUser())

    override suspend fun getLastActiveUser() =
        userMapper.mapperToModel(userDataSource.getLastActiveUser())
}