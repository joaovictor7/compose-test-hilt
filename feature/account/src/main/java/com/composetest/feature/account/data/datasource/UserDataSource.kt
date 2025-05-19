package com.composetest.feature.account.data.datasource

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

    suspend fun getLastActiveUser() = userEntityDao.getLastActiveUser()
}