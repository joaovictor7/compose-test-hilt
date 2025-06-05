package com.composetest.core.data.di

import com.composetest.core.data.R
import com.composetest.core.domain.provider.BuildConfigProvider
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
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
        setDefaultsAsync(R.xml.remote_config_defaults)
        if (!buildConfigProvider.buildConfig.isProduction) {
            setConfigSettingsAsync(
                remoteConfigSettings {
                    minimumFetchIntervalInSeconds = 0
                }
            )
        }
    }
}