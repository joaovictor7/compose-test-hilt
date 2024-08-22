package com.composetest.common.di

import com.composetest.common.di.qualifiers.DispatcherQualifier
import com.composetest.common.enums.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers as Coroutine

@Module
@InstallIn(SingletonComponent::class)
internal object DispatcherModule {
    @Provides
    @DispatcherQualifier(Dispatcher.Default)
    fun defaultDispatcher(): CoroutineDispatcher = Coroutine.Default

    @Provides
    @DispatcherQualifier(Dispatcher.IO)
    fun ioDispatcher(): CoroutineDispatcher = Coroutine.IO

    @Provides
    @DispatcherQualifier(Dispatcher.Main)
    fun mainDispatcher(): CoroutineDispatcher = Coroutine.Main
}
