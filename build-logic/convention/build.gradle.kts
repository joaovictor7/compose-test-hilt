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
            className = "ApplicationConventionPlugin"
        )
        registerPlugin(
            id = "library",
            className = "LibraryConventionPlugin"
        )
        registerPlugin(
            id = "compose",
            className = "ComposeConventionPlugin"
        )
        registerPlugin(
            id = "test",
            className = "TestConventionPlugin"
        )
        registerPlugin(
            id = "kover",
            className = "KoverConventionPlugin"
        )
    }
}