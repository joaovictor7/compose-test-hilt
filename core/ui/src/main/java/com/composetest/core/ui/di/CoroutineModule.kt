package com.composetest.core.ui.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

@Module
@InstallIn(SingletonComponent::class)
internal object CoroutineModule {
    @Provides
    fun coroutineContext(): CoroutineContext = EmptyCoroutineContext
}