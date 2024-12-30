plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.security"
}

dependencies {
    implementation(projects.common)
    implementation(projects.core.domain)
    implementation(projects.core.designsystem)
    implementation(libs.androidx.biometric)
}