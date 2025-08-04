plugins {
    alias(libs.plugins.composetest.application)
    alias(libs.plugins.composetest.compose)
    alias(libs.plugins.composetest.android.test)
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
    projects.feature.exchange,
    projects.feature.form,
    projects.feature.product,
)

dependencies {
    implementation(projects.common)
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)
    implementation(projects.core.router)
    implementation(projects.core.data.impl)
    implementation(projects.core.database.impl)
    implementation(projects.core.domain)
    implementation(projects.core.analytic.impl)
    implementation(projects.core.analytic.api)
    implementation(projects.core.security.impl)
    implementation(platform(libs.firebase.bom))
    implementation(libs.androidx.hilt.work)
    implementation(libs.androidx.splash.screen)
    implementation(libs.androidx.appcompat)
    features.forEach { implementation(it) }
}