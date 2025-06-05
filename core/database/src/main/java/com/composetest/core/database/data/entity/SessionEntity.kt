package com.composetest.core.database.data.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(
    tableName = "Session",
    indices = [Index(value = ["userId"])],
    foreignKeys = [ForeignKey(
        entity = UserEntity::class,
        parentColumns = arrayOf("userId"),
        childColumns = arrayOf("userId")
    )]
)
data class SessionEntity(
    @PrimaryKey(autoGenerate = true) @ColumnInfo("sessionId") val id: Long = 0,
    val token: String,
    val startDate: LocalDateTime,
    val endDate: LocalDateTime,
    val isActive: Boolean = true,
    val userId: String
)
