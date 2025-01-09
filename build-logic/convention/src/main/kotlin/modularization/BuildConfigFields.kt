package modularization

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.ApplicationProductFlavor
import enums.BuildType
import enums.Flavor
import enums.FlavorDimension
import extensions.loadPropertiesFile
import extensions.orEmptyToBuildConfigField
import files.PropertiesFile
import org.gradle.api.Project

internal fun ApplicationBuildType.setBuildConfigFields(
    project: Project,
    buildType: BuildType
) = with(project) {

}

internal fun ApplicationProductFlavor.setBuildConfigFields(
    project: Project,
    dimension: FlavorDimension,
    flavor: Flavor
) = with(project) {
    val apiKeyProperties = loadPropertiesFile(PropertiesFile.ApiKey(flavor))
    val appKeyProperties = loadPropertiesFile(PropertiesFile.AppKey(flavor))
    buildConfigField(
        "String",
        "NEWS_API_KEY",
        apiKeyProperties?.getProperty("NEWS_API_KEY").orEmptyToBuildConfigField
    )
    buildConfigField("String", "OPEN_WEATHER_API_HOST", "\"api.openweathermap.org/data/2.5\"")
    buildConfigField(
        "String",
        "OPEN_WEATHER_API_KEY",
        apiKeyProperties?.getProperty("OPEN_WEATHER_API_KEY").orEmptyToBuildConfigField
    )
    buildConfigField(
        "String",
        "DATABASE_KEY",
        appKeyProperties?.getProperty("DATABASE_KEY").orEmptyToBuildConfigField
    )
}