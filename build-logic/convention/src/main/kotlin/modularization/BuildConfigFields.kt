package modularization

import enums.BuildType
import enums.Flavor
import enums.FlavorDimension
import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.ApplicationDefaultConfig
import com.android.build.api.dsl.ApplicationProductFlavor
import enums.File
import extensions.loadPropertiesFile
import org.gradle.api.Project

internal fun ApplicationDefaultConfig.setDefaultBuildConfigFields(project: Project) =
    with(project) {
        val properties = loadPropertiesFile(File.API_KEYS)
        buildConfigField("String", "BFF_API_HOST", "\"\"") // Macbook
//    buildConfigField("String", "BFF_API_HOST", "\"10.0.2.2\"") // Emulator
        buildConfigField("int", "BFF_API_PORT", "0")
        buildConfigField("String", "NEWS_API_API_KEY", properties.getProperty("NEWS_API"))
    }

internal fun ApplicationBuildType.setBuildConfigFields(
    project: Project,
    buildType: BuildType
) = with(project) {
    when (buildType) {
        BuildType.RELEASE -> {}
        BuildType.DEBUG -> {}
    }
}

internal fun ApplicationProductFlavor.setBuildConfigFields(
    project: Project,
    dimension: FlavorDimension,
    flavor: Flavor
) = with(project) {
    when (flavor) {
        Flavor.FREE -> {}
        Flavor.FULL -> {}
        Flavor.PRODUCTION -> {}
        Flavor.DEVELOP -> {}
        Flavor.STAGING -> {}
    }
}