plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.network"
}

dependencies {
    implementation(projects.core.domain)
    implementation(projects.common)
    implementation(libs.ktor.client.negotiation)
    implementation(libs.ktor.client.serialization)
    implementation(libs.ktor.client.logging)
    implementation(libs.slf4j.api)
    api(libs.ktor.client.android)
}