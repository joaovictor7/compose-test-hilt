package com.composetest.core.analytic.impl.di

import com.google.firebase.Firebase
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.analytics
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.crashlytics.crashlytics
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