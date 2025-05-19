plugins {
    alias(libs.plugins.composeTest.library)
    alias(libs.plugins.composeTest.test)
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
    implementation(projects.feature.home)
    implementation(projects.feature.configuration)
    implementation(projects.feature.account)
    implementation(projects.feature.weatherforecast)
    implementation(projects.feature.news)
    implementation(projects.feature.exchange)
}