plugins {
    alias(libs.plugins.composetest.library)
}

android {
    namespace = "com.composetest.core.security.impl"
}

dependencies {
    implementation(projects.core.security.api)
    implementation(projects.common.api)
    implementation(projects.core.domain)
    implementation(libs.androidx.biometric)
}