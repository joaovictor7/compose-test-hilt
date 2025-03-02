package com.composetest.core.database.partialupdate

import androidx.room.ColumnInfo

data class SecurityConfigurationUpdate(
    @ColumnInfo(name = "configurationId") val id: Long,
    val biometricLogin: Boolean,
)
