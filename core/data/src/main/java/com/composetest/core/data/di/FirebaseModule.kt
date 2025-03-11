package com.composetest.core.data.di

import com.composetest.core.data.R
import com.composetest.core.domain.providers.BuildConfigProvider
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfig
import com.google.firebase.remoteconfig.ktx.remoteConfigSettings
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object FirebaseModule {
    @Provides
    fun firebaseCrashlytics(): FirebaseCrashlytics = Firebase.crashlytics

    @Provides
    fun firebaseAnalytics(): FirebaseAnalytics = Firebase.analytics

    @Provides
    fun firebaseAuth(): FirebaseAuth = Firebase.auth

    @Provides
    @Singleton
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