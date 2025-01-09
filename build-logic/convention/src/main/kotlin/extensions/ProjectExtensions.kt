package extensions

import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.getByType

internal fun Project.getLibrary(id: String) =
    extensions.getByType<VersionCatalogsExtension>().named("libs").findLibrary(id).get()
