package extensions

import files.PropertiesFile
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import java.util.Properties

internal fun Project.getLibrary(id: String) =
    extensions.getByType<VersionCatalogsExtension>().named("libs").findLibrary(id).get()

internal fun Project.loadPropertiesFile(propertiesFile: PropertiesFile): Properties? {
    val rootPropertiesDir = "$rootDir/properties-keys"
    val file = file("$rootPropertiesDir/${propertiesFile.fullyPath}").takeIf { it.exists() }
        ?: file("$rootPropertiesDir/${propertiesFile.file}")
    return if (file.exists()) {
        Properties().apply { load(file.inputStream()) }
    } else {
        println("File '${propertiesFile.fullyPath}' not found.")
        null
    }
}
