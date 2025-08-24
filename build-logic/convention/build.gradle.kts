plugins {
    `kotlin-dsl`
}

dependencies {
    implementation(libs.android.tools.build.gradle)
    implementation(libs.kotlin.gradle.plugin)
    implementation(libs.kotlin.compose.gradle.plugin)
    implementation(libs.kotlin.kover)
}

gradlePlugin {
    plugins {
        fun registerPlugin(id: String, className: String) {
            register(id) {
                this.id = "com.composetest.$id"
                this.implementationClass = className
            }
        }
        registerPlugin(
            id = "application",
            className = "ApplicationConventionPlugin",
        )
        registerPlugin(
            id = "library",
            className = "AndroidLibraryConventionPlugin",
        )
        registerPlugin(
            id = "compose",
            className = "ComposeConventionPlugin",
        )
        registerPlugin(
            id = "test",
            className = "TestConventionPlugin",
        )
        registerPlugin(
            id = "androidTest",
            className = "AndroidTestConventionPlugin",
        )
        registerPlugin(
            id = "kover",
            className = "KoverConventionPlugin",
        )
        registerPlugin(
            id = "kotlin",
            className = "KotlinLibraryConventionPlugin",
        )
    }
}