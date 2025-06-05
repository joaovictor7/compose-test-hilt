package com.composetest.core.analytic.di

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.ktx.crashlytics
import com.google.firebase.ktx.Firebase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object FirebaseModule {
    @Provides
    fun firebaseCrashlytics(): FirebaseCrashlytics = Firebase.crashlytics

    @Provides
    fun firebaseAnalytics(): FirebaseAnalytics = Firebase.analytics
}