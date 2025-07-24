plugins {
    alias(libs.plugins.composetest.library)
    alias(libs.plugins.composetest.compose)
}

android {
    namespace = "com.composetest.core.designsystem"
}

dependencies {
    implementation(projects.common)
    implementation(projects.core.network)
    implementation(projects.core.domain)
    implementation(projects.core.ui)
    implementation(projects.core.router)
    implementation(libs.androidx.appcompat)
    implementation(libs.coil)
    implementation(libs.coil.network)
}