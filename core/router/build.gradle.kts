plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.compose)
}

android {
    namespace = "com.composetest.core.router"
}

dependencies {
    implementation(projects.common)
    implementation(projects.core.network)
    implementation(libs.kotlin.reflect)
    implementation(libs.kotlin.json.serializable)
}