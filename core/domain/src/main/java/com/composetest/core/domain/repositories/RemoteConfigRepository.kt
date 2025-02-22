package com.composetest.core.domain.repositories

interface RemoteConfigRepository {
    fun getString(key: String): String
    fun getLong(key: String): Long
    fun getDouble(key: String): Double
    fun fetch()
}