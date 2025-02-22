plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.composetest.core.network"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.common)
    implementation(libs.kotlin.json.serializable)
    implementation(libs.ktor.client.android)
    implementation(libs.ktor.client.negotiation)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.logging)
    implementation(libs.slf4j.api)
}