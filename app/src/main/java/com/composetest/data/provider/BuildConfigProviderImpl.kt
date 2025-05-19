package com.composetest.data.provider

import android.os.Build
import com.composetest.BuildConfig
import com.composetest.core.domain.enums.BuildType.Companion.getBuildType
import com.composetest.core.domain.enums.Flavor.Companion.getFlavor
import com.composetest.core.domain.model.buildconfig.BuildConfigFieldsModel
import com.composetest.core.domain.model.buildconfig.BuildConfigModel
import com.composetest.core.domain.provider.BuildConfigProvider
import javax.inject.Inject

internal class BuildConfigProviderImpl @Inject constructor() : BuildConfigProvider {

    override val buildConfig = BuildConfigModel(
        applicationId = BuildConfig.APPLICATION_ID,
        versionName = BuildConfig.VERSION_NAME,
        versionCode = BuildConfig.VERSION_CODE,
        buildType = BuildConfig.BUILD_TYPE.getBuildType(),
        flavor = BuildConfig.FLAVOR.getFlavor(),
        androidSdkVersion = Build.VERSION.SDK_INT,
    )

    override val buildConfigFields = BuildConfigFieldsModel(
        coinApiHost = BuildConfig.COIN_API_HOST,
        newsApiHost = BuildConfig.NEWS_API_HOST,
        openWeatherApiHost = BuildConfig.OPEN_WEATHER_API_HOST,
        openWeatherIconHost = BuildConfig.OPEN_WEATHER_ICON_HOST,
    )
}