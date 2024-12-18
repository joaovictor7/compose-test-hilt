package modularization

import com.android.build.api.dsl.ApplicationBuildType
import com.android.build.api.dsl.ApplicationDefaultConfig
import com.android.build.api.dsl.ApplicationProductFlavor
import enums.BuildType
import enums.File
import enums.Flavor
import enums.FlavorDimension
import extensions.loadPropertiesFile
import org.gradle.api.Project

internal fun ApplicationDefaultConfig.setDefaultBuildConfigFields(project: Project) =
    with(project) {
        val properties = loadPropertiesFile(File.API_KEYS)
        buildConfigField("String", "BFF_API_HOST", "\"\"") // Macbook
//    buildConfigField("String", "BFF_API_HOST", "\"10.0.2.2\"") // Emulator
        buildConfigField("int", "BFF_API_PORT", "0")
        buildConfigField(
            "String",
            "NEWS_API_API_KEY",
            properties?.getProperty("NEWS_API").orEmpty()
        )
    }

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

}