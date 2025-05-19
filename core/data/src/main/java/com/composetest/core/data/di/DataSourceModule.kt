package com.composetest.core.data.di

import com.composetest.core.data.datasource.PreferenceDataSource
import com.composetest.core.data.datasource.local.PreferenceDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal object DataSourceModule {

    @Provides
    fun preferencesDataSource(
        preferenceDataSourceImpl: PreferenceDataSourceImpl
    ): PreferenceDataSource = preferenceDataSourceImpl
}