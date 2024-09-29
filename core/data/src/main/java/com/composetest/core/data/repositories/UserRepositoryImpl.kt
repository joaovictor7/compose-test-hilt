package com.composetest.core.data.repositories

import com.composetest.core.data.datasources.local.UserDataSource
import com.composetest.core.data.mappers.UserMapper
import com.composetest.core.domain.models.UserModel
import com.composetest.core.domain.repositories.UserRepository
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource,
    private val userMapper: UserMapper
) : UserRepository {

    override suspend fun upsert(user: UserModel) {
        userDataSource.upsert(userMapper(user))
    }

    override suspend fun getCurrentUser() =
        userMapper(userDataSource.getCurrentUser())
}