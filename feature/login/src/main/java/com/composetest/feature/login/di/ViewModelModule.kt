package com.composetest.feature.login.di

import androidx.lifecycle.SavedStateHandle
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.core.router.extensions.getDestination
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object ViewModelModule {
    @Provides
    fun loginDestination(
        savedStateHandle: SavedStateHandle
    ): LoginDestination = savedStateHandle.getDestination()
}