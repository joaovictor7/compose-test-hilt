package com.composetest.core.data.datasources.local

import com.composetest.core.database.daos.UserEntityDao
import com.composetest.core.database.entities.UserEntity
import javax.inject.Inject

internal class UserDataSource @Inject constructor(
    private val userEntityDao: UserEntityDao
) {

    suspend fun upsert(user: UserEntity) {
        userEntityDao.upsert(user)
    }

    suspend fun getCurrentUser() = userEntityDao.getCurrentUser()

    suspend fun getLastUser() = userEntityDao.getLastUser()
}