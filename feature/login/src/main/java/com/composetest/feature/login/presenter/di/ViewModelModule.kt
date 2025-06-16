package com.composetest.feature.login.presenter.di

import androidx.lifecycle.SavedStateHandle
import com.composetest.core.analytic.sender.AnalyticSender
import com.composetest.core.router.destination.login.LoginDestination
import com.composetest.core.router.extension.getDestination
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.login.analytic.screen.LoginScreenAnalytic
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
    @AsyncTaskUtilsQualifier(LoginScreenAnalytic.SCREEN)
    fun loginAsyncTaskUtils(
        analyticSender: AnalyticSender
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, LoginScreenAnalytic)
}