package com.composetest.feature.form.presenter.di

import com.composetest.core.analytic.api.sender.AnalyticSender
import com.composetest.core.ui.di.qualifier.AsyncTaskUtilsQualifier
import com.composetest.core.ui.util.AsyncTaskUtils
import com.composetest.feature.form.analytic.screen.FormScreenAnalytic
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
internal object ViewModelModule {

    @Provides
    @AsyncTaskUtilsQualifier(FormScreenAnalytic.SCREEN)
    fun formAsyncTaskUtils(
        analyticSender: AnalyticSender
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, FormScreenAnalytic)
}