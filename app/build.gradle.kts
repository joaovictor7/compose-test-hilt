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

dependencies {
    implementation(projects.common)
    implementation(projects.core.designsystem)
    implementation(projects.core.ui)
    implementation(projects.core.router)
    implementation(projects.core.database)
    implementation(projects.core.data)
    implementation(projects.core.domain)
    implementation(projects.core.analytic)
    implementation(projects.feature.login)
    implementation(projects.feature.root)
    implementation(projects.feature.home)
    implementation(projects.feature.account)
    implementation(projects.feature.configuration)
    implementation(projects.feature.weatherforecast)
    implementation(projects.feature.news)
    implementation(projects.feature.exchange)
    implementation(platform(libs.firebase.bom))
    implementation(libs.androidx.hilt.work)
    implementation(libs.androidx.splash.screen)
    implementation(libs.androidx.appcompat)
    implementation(libs.androidx.startup)
}