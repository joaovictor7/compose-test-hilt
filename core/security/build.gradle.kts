plugins {
    alias(libs.plugins.composeTest.library)
}

android {
    namespace = "com.composetest.core.security"
}

dependencies {
    implementation(projects.core.designsystem)
    implementation(libs.sql.cipher)
    implementation(libs.sqlite)
    implementation(libs.androidx.biometric)
}