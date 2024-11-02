package com.composetest.core.data.datasources.remote

internal interface FirebaseRemoteConfigDataSource {
    fun getString(key: String): String
    fun getBoolean(key: String): Boolean
    fun getLong(key: String): Long
    fun getDouble(key: String): Double
    fun fetch()
}