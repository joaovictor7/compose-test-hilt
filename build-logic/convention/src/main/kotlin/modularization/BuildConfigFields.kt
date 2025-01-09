package modularization

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.ApplicationProductFlavor
import enums.BuildType
import enums.Flavor
import extensions.getPropertyOrEmpty
import extensions.loadPropertiesFile
import files.PropertiesFile
import org.gradle.api.Project

internal fun ApplicationBuildType.setBuildConfigFields(
    project: Project,
    buildType: BuildType
) = with(project) {
    val appProperties = loadPropertiesFile(PropertiesFile.App(buildType::class))
    buildConfigField(
        "String",
        "DATABASE_KEY",
        appProperties.getPropertyOrEmpty("DATABASE_KEY")
    )
}

internal fun ApplicationProductFlavor.setBuildConfigFields(
    project: Project,
    flavor: Flavor
) = with(project) {
    val appProperties = loadPropertiesFile(PropertiesFile.App(flavor::class))
    buildConfigField(
        "String",
        "NEWS_API_KEY",
        appProperties.getPropertyOrEmpty("NEWS_API_KEY")
    )
    buildConfigField(
        "String",
        "OPEN_WEATHER_API_HOST",
        appProperties.getPropertyOrEmpty("OPEN_WEATHER_API_HOST")
    )
    buildConfigField(
        "String",
        "OPEN_WEATHER_API_KEY",
        appProperties.getPropertyOrEmpty("OPEN_WEATHER_API_KEY")
    )
}