plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.security.impl"
}

dependencies {
    implementation(projects.core.security.api)
    implementation(projects.common)
    implementation(projects.core.domain)
    implementation(libs.androidx.biometric)
}