package com.composetest.core.database.data.entity.configuration

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.composetest.core.database.data.entity.UserEntity

@Entity(
    tableName = "Configuration",
    indices = [Index(value = ["userId"], unique = true)],
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["userId"],
            childColumns = ["userId"],
            onDelete = ForeignKey.CASCADE
        )
    ],
)
data class ConfigurationEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "configurationId") val id: Long,
    val userId: String,
    @Embedded val securityEntity: SecurityConfigurationEntity,
)
