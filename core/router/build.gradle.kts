plugins {
    alias(libs.plugins.composetest.kotlin.library)
    alias(libs.plugins.composetest.compose)
}

android {
    namespace = "com.composetest.core.router"
}

dependencies {
    implementation(projects.common.api)
    implementation(projects.core.network)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.json.serializable)
}