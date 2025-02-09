package com.composetest.feature.exchange.di

import androidx.lifecycle.SavedStateHandle
import com.composetest.core.router.destinations.exchange.ExchangeDetailDestination
import com.composetest.core.router.extensions.getDestination
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object ViewModelModule {

    @Provides
    fun provideExchangeDetailDestination(
        savedStateHandle: SavedStateHandle
    ): ExchangeDetailDestination = savedStateHandle.getDestination()
}