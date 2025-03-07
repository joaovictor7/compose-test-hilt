plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.test)
    alias(libs.plugins.composeTest.compose)
}

android {
    namespace = "com.composetest.feature.weatherforecast"
}

dependencies {
    implementation(projects.common)
    implementation(projects.core.ui)
    implementation(projects.core.designsystem)
    implementation(projects.core.network)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.router)
    implementation(projects.core.analytic)
}