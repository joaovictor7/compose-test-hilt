package com.composetest.core.data.impl.di

import com.composetest.core.domain.provider.BuildConfigProvider
import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object FirebaseModule {

    @Provides
    fun firebaseRemoteConfig(
        buildConfigProvider: BuildConfigProvider
    ): FirebaseRemoteConfig = Firebase.remoteConfig.apply {
        if (!buildConfigProvider.buildConfig.isProduction) {
            setConfigSettingsAsync(remoteConfigSettings {
                minimumFetchIntervalInSeconds = 0
            })
        }
    }
}