plugins {
    alias(libs.plugins.composeTest.application)
    alias(libs.plugins.composeTest.compose)
    alias(libs.plugins.composeTest.test)
}

android {
    val appPackage = "com.composetest"
    namespace = appPackage
    defaultConfig {
        applicationId = appPackage
    }
}

private val features = listOf(
    projects.feature.login,
    projects.feature.root,
    projects.feature.home,
    projects.feature.account,
    projects.feature.configuration,
    projects.feature.weatherforecast,
    projects.feature.news,
    projects.feature.exchange
)

dependencies {
    implementation(projects.common)
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)
    implementation(projects.core.router)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.analytic)
    implementation(platform(libs.firebase.bom))
    implementation(libs.androidx.hilt.work)
    implementation(libs.androidx.splash.screen)
    implementation(libs.androidx.appcompat)
    features.forEach { implementation(it) }
}