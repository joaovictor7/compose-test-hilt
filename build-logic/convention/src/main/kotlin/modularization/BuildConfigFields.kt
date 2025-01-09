package modularization

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.ApplicationProductFlavor
import enums.BuildType
import enums.Flavor
import files.LoadPropertiesFile
import org.gradle.api.Project

internal fun ApplicationBuildType.setBuildConfigFields(
    project: Project,
    buildType: BuildType
) {
    val properties = LoadPropertiesFile(project, buildType)
    buildConfigField(
        "String",
        "DATABASE_KEY",
        properties.getProperty("DATABASE_KEY")
    )
}

internal fun ApplicationProductFlavor.setBuildConfigFields(
    project: Project,
    flavor: Flavor
) {
    val properties = LoadPropertiesFile(project, flavor)
    buildConfigField(
        "String",
        "NEWS_API_HOST",
        properties.getProperty("NEWS_API_HOST"),
    )
    buildConfigField(
        "String",
        "NEWS_API_KEY",
        properties.getProperty("NEWS_API_KEY")
    )
    buildConfigField(
        "String",
        "OPEN_WEATHER_ICON_HOST",
        properties.getProperty("OPEN_WEATHER_ICON_HOST")
    )
    buildConfigField(
        "String",
        "OPEN_WEATHER_API_HOST",
        properties.getProperty("OPEN_WEATHER_API_HOST")
    )
    buildConfigField(
        "String",
        "OPEN_WEATHER_API_KEY",
        properties.getProperty("OPEN_WEATHER_API_KEY")
    )
}