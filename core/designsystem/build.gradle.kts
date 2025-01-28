plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.compose)
}

android {
    namespace = "com.composetest.core.designsystem"
}

dependencies {
    implementation(projects.common)
    implementation(projects.core.domain)
    implementation(libs.androidx.appcompat)
    implementation(libs.coil)
    implementation(libs.coil.network)
}