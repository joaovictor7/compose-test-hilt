plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.compose)
    alias(libs.plugins.kotlinSerialization)
    id("kotlin-parcelize")
}

android {
    namespace = "com.composetest.core.router"
}

dependencies {
    implementation(projects.common)
    implementation(projects.core.ui)
    implementation(libs.kotlin.json.serializable)
    implementation(libs.kotlin.reflect)
}