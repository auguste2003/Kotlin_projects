plugins {
    alias(libs.plugins.android.application) // Zum Erstellen der APK notwendig
    alias(libs.plugins.jetbrains.kotlin.android) // Integriert Kotlin in meinem Projekt anstelle von Java
    id("org.jetbrains.kotlin.plugin.serialization") version "1.9.10"
}

android {
    namespace = "com.example.compose_kurs"
    compileSdk = 34 // SDK Version

    defaultConfig {// Standardkonfiguration der App
        applicationId = "com.example.compose_kurs" // Kennung der App in Appstore
        minSdk = 24  // Minimale SDK Version um die App zu installieren
        targetSdk = 34 // Diese Version ist optimal
        versionCode = 1 // Wird bei jedem Opdate inkrementiert
        versionName = "1.0" // Für den Benutzer sichtbar

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner" // Runner für den Instrumentationstest
        vectorDrawables {
            useSupportLibrary = true  // Unterstützung von Vector drawable auf alte Version
        }
    }

    buildTypes {
        release {// release und nicht debug  als Buil-konfiguration
            isMinifyEnabled = false // steur ob der Code minimiert wird
            proguardFiles( // ProGuard wird verwendet, um den Code zu optimieren und zu verschleiern.
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {// Optionen der Kompilierung von Java-versionen
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions { // definiert die Option der Kompilierung von Kotlin-Code
        jvmTarget = "1.8" // JVM 1.8
    }
    buildFeatures { // Aktiviert und deaktiviert bestimmet Features in dem Buld
        compose = true // Aktiviert jetpack-Compose
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.1" // Version der Kotlin-Compiler-Erweiterung
    }
    packaging {// Dfiniert wo die App gepackt werden
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {// Bicliotheken werden hier aufgelistet

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
   // implementation(libs.androidx.material3.android)

    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.6.2") // LiveData
    implementation("androidx.constraintlayout:constraintlayout-compose:1.0.1") // ConstraintLayout
    implementation("io.coil-kt:coil-compose:2.4.0") // Coil
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.5.1") // Serializable
    implementation(libs.androidx.navigation.common.ktx)
    implementation(libs.androidx.navigation.runtime.ktx)
    implementation(libs.androidx.navigation.compose) // coil
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}