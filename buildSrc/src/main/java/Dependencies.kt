/**
 * To define dependencies
 */
object Dependencies {

    // Core Android
    val coreAndroidX by lazy {"androidx.core:core-ktx:${Versions.coreAndroidX}"}

    // Compose
    val uiCompose by lazy { "androidx.compose.ui:ui:${Versions.composeVersion}" }
    val materialCompose by lazy { "androidx.compose.material:material:${Versions.composeVersion}" }
    val uiToolingPreviewCompose by lazy { "androidx.compose.ui:ui-tooling-preview:${Versions.composeVersion}" }
    val uiTestJUnit4Compose by lazy { "androidx.compose.ui:ui-test-junit4:${Versions.composeVersion}" }
    val uiToolingCompose by lazy { "androidx.compose.ui:ui-tooling:${Versions.composeVersion}" }
    val runtimeLiveData by lazy { "androidx.compose.runtime:runtime-livedata:${Versions.composeVersion}"}
    val navigationCompose by lazy { "androidx.navigation:navigation-compose:${Versions.navigationCompose}"}

    val iconsExtended by lazy {"androidx.compose.material:material-icons-extended:${Versions.composeVersion}"}

    val lifecycleRuntime by lazy {"androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycleAndroidX}"}
    val lifecycleLivedata by lazy {"androidx.lifecycle:lifecycle-livedata-ktx:${Versions.lifecycleAndroidX}"}
    val lifecycleViewModel by lazy {"androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycleAndroidX}"}
    val lifecycleViewModelCompose by lazy {"androidx.lifecycle:lifecycle-viewmodel-compose:${Versions.lifecycleAndroidX}"}

    // Activity Compose
    val activityCompose by lazy {"androidx.activity:activity-compose:${Versions.activityCompose}"}

    // Tests
    val junit by lazy {"junit:junit:${Versions.junit}"}
    val junitAndroidX by lazy {"androidx.test.ext:junit:1.1.3:${Versions.junitAndroidX}"}
    val expressoAndroidX by lazy {"androidx.test.espresso:espresso-core:${Versions.expressoAndroidX}"}

    // Hilt
    val hiltAndroid by lazy { "com.google.dagger:hilt-android:${Versions.hiltVersion}" }
    val hiltAndroidCompiler by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hiltVersion}" }

    val hiltNavigationCompose by lazy {"androidx.hilt:hilt-navigation-compose:${Versions.hiltNavigationComposeVersion}"}

    // Room
    val roomRuntine by lazy {"androidx.room:room-runtime:${Versions.roomVersion}"}
    val roomKtx by lazy {"androidx.room:room-ktx:${Versions.roomVersion}"}
    val roomCompiler by lazy {"androidx.room:room-compiler:${Versions.roomVersion}"}

    val accompanistInsets by lazy {"com.google.accompanist:accompanist-insets:${Versions.accompanistVersion}"}

    val coilCompose by lazy {"io.coil-kt:coil-compose:${Versions.coilVersion}"}

    val appCompat by lazy {"androidx.appcompat:appcompat:${Versions.appCompat}"}

    val workRuntime by lazy { "androidx.work:work-runtime-ktx:${Versions.workRuntine}"}
    //annotationProcessor("androidx.room:room-compiler:$roomVersion")

     val datastorePreferences by lazy {"androidx.datastore:datastore-preferences:${Versions.datastoreVersion}"}

    val constraintLayoutCompose by lazy {"androidx.constraintlayout:constraintlayout-compose:${Versions.constraintLayout}"}
    val GSon by lazy {"com.google.code.gson:gson:${Versions.gsonVersion}"}

    // To use Kotlin annotation processing tool (kapt)
    //kapt("androidx.room:room-compiler:$roomVersion")
    // To use Kotlin Symbolic Processing (KSP)
    //ksp("androidx.room:room-compiler:$roomVersion")
}


/*
object Versions {
    const val ktlint = "0.41.0"
}

object Libs {
    const val androidGradlePlugin = "com.android.tools.build:gradle:7.0.0"
    const val hiltAndroidGradlePlugin = "com.google.dagger:hilt-android-gradle-plugin:2.38.1"

    object Accompanist {
        private const val version = "0.16.0"
        const val insets = "com.google.accompanist:accompanist-insets:$version"
    }

    object Kotlin {
        private const val version = "1.5.21"
        const val stdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"
        const val extensions = "org.jetbrains.kotlin:kotlin-android-extensions:$version"
    }

    object Coroutines {
        private const val version = "1.5.1"
        const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
        const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
        const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
    }

    object Hilt {
        private const val version = "2.38.1"
        const val hiltAndroid = "com.google.dagger:hilt-android:$version"
        const val hiltAndroidCompiler = "com.google.dagger:hilt-android-compiler:$version"
    }

    object JUnit {
        private const val version = "4.13"
        const val junit = "junit:junit:$version"
    }

    object JSON {
        private const val version = "2.8.2"
        const val GSon = "com.google.code.gson:gson:$version"
    }

    object AndroidX {
        const val coreKtx = "androidx.core:core-ktx:1.6.0"
        const val navigation = "androidx.navigation:navigation-compose:2.4.0-alpha06"

        object Activity {
            const val activityCompose = "androidx.activity:activity-compose:1.3.1"
        }

        object Compose {
            const val snapshot = ""
            private const val version = "1.0.1"

            const val animation = "androidx.compose.animation:animation:$version"
            const val foundation = "androidx.compose.foundation:foundation:$version"
            const val layout = "androidx.compose.foundation:foundation-layout:$version"
            const val iconsExtended = "androidx.compose.material:material-icons-extended:$version"
            const val material = "androidx.compose.material:material:$version"
            const val runtime = "androidx.compose.runtime:runtime:$version"
            const val tooling = "androidx.compose.ui:ui-tooling:$version"
            const val ui = "androidx.compose.ui:ui:$version"
            const val uiUtil = "androidx.compose.ui:ui-util:$version"
            const val uiTest = "androidx.compose.ui:ui-test-junit4:$version"
            const val uiTestManifest = "androidx.compose.ui:ui-test-manifest:$version"
            const val runtimeLiveData = "androidx.compose.runtime:runtime-livedata:$version"
        }

        object Room {
            private const val version = "2.3.0"
            const val room = "androidx.room:room-ktx:$version"
            const val roomCompiler = "androidx.room:room-compiler:$version"
            const val roomTesting = "androidx.room:room-testing:$version"
        }

        object ConstraintLayout {
            const val constraintLayoutCompose =
                "androidx.constraintlayout:constraintlayout-compose:1.0.0-beta02"
        }

        object Coroutine {
            private const val version = "2.7.0"
            const val workRuntime = "androidx.work:work-runtime-ktx:$version"
        }

        object Lifecycle {
            private const val version = "2.3.1"
            const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val lifecycleLivedata = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val lifecycleCommon = "androidx.lifecycle:lifecycle-common-java8:$version"
        }

        object HiltLifecycle {
            // Hilt Jetpack Integrations

            private const val version = "1.0.0-beta01"
            //const val hiltLifecycleViewModel = "androidx.hilt:hilt-lifecycle-viewmodel:$version"
            //const val hiltLifecycleCompiler = "androidx.hilt:hilt-compiler:$version"
            const val hiltLifecycleNavigationCompose = "androidx.hilt:hilt-navigation-compose:$version"
        }

        object Datastore {
            private const val version = "1.0.0"
            const val datastorePreferences = "androidx.datastore:datastore-preferences:$version"
        }


        object Test {
            private const val version = "1.3.0"
            const val core = "androidx.test:core:$version"
            const val rules = "androidx.test:rules:$version"

            object Ext {
                private const val version = "1.1.2"
                const val junit = "androidx.test.ext:junit-ktx:$version"
            }

            const val espressoCore = "androidx.test.espresso:espresso-core:3.3.0"
        }
    }

    object Coil {
        const val coilCompose = "io.coil-kt:coil-compose:1.3.0"
    }





    implementation(Dependencies.coreAndroidX)

    implementation(Dependencies.uiCompose)
    implementation (Dependencies.materialCompose)
    implementation (Dependencies.uiToolingPreviewCompose)
    implementation(Dependencies.runtimeLiveData)
    androidTestImplementation (Dependencies.uiTestJUnit4Compose)
    debugImplementation (Dependencies.uiToolingCompose)

    implementation (Dependencies.lifecycleRuntime)
    implementation (Dependencies.lifecycleLivedata)
    implementation (Dependencies.lifecycleViewModel)
    implementation (Dependencies.lifecycleViewModelCompose)
    implementation (Dependencies.activityCompose)
    testImplementation (Dependencies.junit)

    androidTestImplementation (Dependencies.junitAndroidX)
    debugImplementation (Dependencies.expressoAndroidX)

    implementation (Dependencies.hiltAndroid)
    kapt(Dependencies.hiltAndroidCompiler)

    implementation (Dependencies.hiltNavigationCompose)

    implementation (Dependencies.roomRuntine)
    implementation (Dependencies.roomKtx)
    annotationProcessor (Dependencies.roomCompiler)
    kapt(Dependencies.roomCompiler)

    implementation (Dependencies.accompanistInsets)
    implementation (Dependencies.coilCompose)

    implementation (Dependencies.appCompat)




 */
