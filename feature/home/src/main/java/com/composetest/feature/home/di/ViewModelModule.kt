package com.composetest.feature.home.di

import androidx.lifecycle.SavedStateHandle
import com.composetest.core.router.destinations.home.Home3Destination
import com.composetest.core.router.extensions.getDestination
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object ViewModelModule {

    @Provides
    fun home3Destination(
        savedStateHandle: SavedStateHandle
    ): Home3Destination = savedStateHandle.getDestination()
}