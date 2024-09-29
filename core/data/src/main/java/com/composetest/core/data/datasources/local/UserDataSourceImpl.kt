package com.composetest.core.data.datasources.local

import com.composetest.core.database.daos.UserEntityDao
import com.composetest.core.database.entities.UserEntity
import javax.inject.Inject

internal class UserDataSourceImpl @Inject constructor(
    private val userEntityDao: UserEntityDao
) : UserDataSource {

    override suspend fun upsert(user: UserEntity) {
        userEntityDao.upsert(user)
    }

    override suspend fun getCurrentUser() = userEntityDao.getCurrentUser()
}