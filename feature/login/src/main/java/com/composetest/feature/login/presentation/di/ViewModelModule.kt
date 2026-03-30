package com.composetest.feature.login.presentation.di

import androidx.lifecycle.SavedStateHandle
import com.composetest.core.analytic.api.sender.AnalyticSender
import com.composetest.core.router.extension.getNavKey
import com.composetest.core.router.navkey.login.LoginNavKey
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
    fun loginNavKey(
        savedStateHandle: SavedStateHandle
    ): LoginNavKey = savedStateHandle.getNavKey()

    @Provides
    @AsyncTaskUtilsQualifier(LoginScreenAnalytic.SCREEN)
    fun loginAsyncTaskUtils(
        analyticSender: AnalyticSender
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, LoginScreenAnalytic)
}