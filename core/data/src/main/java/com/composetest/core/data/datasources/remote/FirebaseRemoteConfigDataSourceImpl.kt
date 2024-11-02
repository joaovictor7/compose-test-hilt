package com.composetest.core.data.datasources.remote

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import javax.inject.Inject

internal class FirebaseRemoteConfigDataSourceImpl @Inject constructor(
    private val firebaseRemoteConfig: FirebaseRemoteConfig,
) : FirebaseRemoteConfigDataSource {

    override fun getString(key: String) = firebaseRemoteConfig.getString(key)
    override fun getBoolean(key: String) = firebaseRemoteConfig.getBoolean(key)
    override fun getLong(key: String) = firebaseRemoteConfig.getLong(key)
    override fun getDouble(key: String) = firebaseRemoteConfig.getDouble(key)
    override fun fetch() {
        firebaseRemoteConfig.fetchAndActivate()
    }
}