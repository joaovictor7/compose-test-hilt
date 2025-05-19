package com.composetest.core.data.datasource.remote

import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import javax.inject.Inject

internal class RemoteConfigDataSource @Inject constructor(
    private val firebaseRemoteConfig: FirebaseRemoteConfig,
) {

    fun getString(key: String) = firebaseRemoteConfig.getString(key)

    fun getLong(key: String) = firebaseRemoteConfig.getLong(key)

    fun getDouble(key: String) = firebaseRemoteConfig.getDouble(key)

    fun fetch() {
        firebaseRemoteConfig.fetchAndActivate()
    }
}