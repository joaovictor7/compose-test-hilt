package com.composetest.feature.news.di

import androidx.lifecycle.SavedStateHandle
import com.composetest.core.router.destinations.news.FullNewsDestination
import com.composetest.core.router.extensions.getDestination
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object ViewModelModule {
    @Provides
    fun fullNewsDestination(
        savedStateHandle: SavedStateHandle
    ): FullNewsDestination = savedStateHandle.getDestination()
}