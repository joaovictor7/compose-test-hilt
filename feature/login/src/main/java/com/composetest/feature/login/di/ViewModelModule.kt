package com.composetest.feature.login.di

import androidx.lifecycle.SavedStateHandle
import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.enums.ScreensAnalytic
import com.composetest.core.router.destinations.login.LoginDestination
import com.composetest.core.router.extensions.getDestination
import com.composetest.core.ui.di.qualifiers.AsyncTaskUtilsQualifier
import com.composetest.core.ui.utils.AsyncTaskUtils
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

    @Provides
    @AsyncTaskUtilsQualifier(ScreensAnalytic.LOGIN)
    fun loginAsyncTaskUtils(
        analyticSender: AnalyticSender
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, ScreensAnalytic.LOGIN)
}