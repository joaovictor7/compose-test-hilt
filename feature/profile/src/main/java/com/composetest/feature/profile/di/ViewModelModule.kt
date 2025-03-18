package com.composetest.feature.profile.di

import com.composetest.core.analytic.AnalyticSender
import com.composetest.core.analytic.events.profile.EditProfileScreenAnalytic
import com.composetest.core.analytic.events.profile.ProfileScreenAnalytic
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
    @AsyncTaskUtilsQualifier(EditProfileScreenAnalytic.SCREEN)
    fun editProfileAsyncTaskUtils(
        analyticSender: AnalyticSender,
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, EditProfileScreenAnalytic)

    @Provides
    @AsyncTaskUtilsQualifier(ProfileScreenAnalytic.SCREEN)
    fun profileAsyncTaskUtils(
        analyticSender: AnalyticSender,
    ): AsyncTaskUtils = AsyncTaskUtils(analyticSender, ProfileScreenAnalytic)
}