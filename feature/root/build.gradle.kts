plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.androidTest)
    alias(libs.plugins.composeTest.compose)
}

android {
    namespace = "com.composetest.feature.root"
}

dependencies {
    implementation(projects.common)
    implementation(projects.core.router)
    implementation(projects.core.designsystem)
    implementation(projects.core.domain)
    implementation(projects.core.ui)
    implementation(projects.core.analytic)
}