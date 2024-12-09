package extensions

import enums.File
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType
import java.util.Properties

internal fun Project.getLibrary(id: String) =
    extensions.getByType<VersionCatalogsExtension>().named("libs").findLibrary(id).get()

internal fun Project.loadPropertiesFile(file: File) = file(file.path).let {
    if (it.exists()) {
        Properties().apply { load(it.inputStream()) }
    } else {
        println("File '${file.path}' not found.")
        null
    }
}
