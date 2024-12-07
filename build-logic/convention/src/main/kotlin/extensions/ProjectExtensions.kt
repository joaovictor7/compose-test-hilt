package extensions

import enums.File
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import java.io.FileNotFoundException
import java.util.Properties

internal fun Project.getLibrary(id: String) =
    extensions.getByType<VersionCatalogsExtension>().named("libs").findLibrary(id).get()

internal fun Project.loadPropertiesFile(file: File): Properties {
    val propertiesFile = file(file.path)
    return Properties().apply {
        if (propertiesFile.exists()) {
            propertiesFile.inputStream().use { load(it) }
        } else {
            throw FileNotFoundException("File '${file.path}' not found.")
        }
    }
}
