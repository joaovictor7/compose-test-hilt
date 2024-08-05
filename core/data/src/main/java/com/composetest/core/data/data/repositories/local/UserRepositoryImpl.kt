package com.composetest.core.data.data.repositories.local

import com.composetest.core.data.data.datasources.local.UserDataSource
import com.composetest.core.database.entities.UserEntity
import javax.inject.Inject

internal class UserRepositoryImpl @Inject constructor(
    private val userDataSource: UserDataSource
) : UserRepository {

    override suspend fun insert(user: UserEntity) {
        userDataSource.insert(user)
    }

    override suspend fun getCurrentUser() = userDataSource.getCurrentUser()
}