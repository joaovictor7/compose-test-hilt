package modularization

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.ApplicationProductFlavor
import enum.BuildType
import enum.Flavor
import file.LoadPropertiesFile
import org.gradle.api.Project

internal fun ApplicationBuildType.setBuildConfigFields(
    project: Project,
    buildType: BuildType
) {
}

internal fun ApplicationProductFlavor.setBuildConfigFields(
    project: Project,
    flavor: Flavor
) {
    val properties = LoadPropertiesFile(project, flavor)
    buildConfigField("String", "COIN_API_HOST", properties.getProperty("host.coin-api"))
    buildConfigField("String", "NEWS_API_HOST", properties.getProperty("host.news-api"))
    buildConfigField(
        "String",
        "OPEN_WEATHER_API_HOST",
        properties.getProperty("host.open-weather-api")
    )
    buildConfigField(
        "String",
        "OPEN_WEATHER_ICON_HOST",
        properties.getProperty("host.open-weather-icon")
    )
}