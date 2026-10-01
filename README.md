# cloudPlus

An Android app scaffolded with Kotlin and Jetpack Compose.

Built on **AGP 9.2.1**, which has **built-in Kotlin support**. That is the most
important thing to know about this project: the `org.jetbrains.kotlin.android`
plugin is deliberately *not* applied anywhere. Adding it will fail the build with
> The 'org.jetbrains.kotlin.android' plugin is no longer required for Kotlin
> support since AGP 9.0.

## Requirements

| Tool | Version | Notes |
| --- | --- | --- |
| JDK | 17 (LTS) | `JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64` |
| Android SDK | platform 36, build-tools 36.0.0 | `ANDROID_HOME=/home/umbo/Android/Sdk` |
| Gradle | 9.4.1 | Use the bundled wrapper, `./gradlew` |

The `gradle` binary on this machine is 4.4.1, which is far too old for AGP 9.
Always build through the wrapper.

## Setup

```bash
cd /home/umbo/projects/cloudPlus

export JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64
export ANDROID_HOME=/home/umbo/Android/Sdk
```

`local.properties` already points at the SDK. It is git-ignored, so on a new
machine create it yourself:

```bash
echo "sdk.dir=$ANDROID_HOME" > local.properties
```

Accept the Android SDK licenses, if you have not already:

```bash
yes | sdkmanager --licenses
```

## Build

```bash
./gradlew assembleDebug        # debug APK
./gradlew assembleRelease      # release APK (minified, needs a signing key)
./gradlew installDebug         # build and install on a connected device
./gradlew clean                # remove build output
```

The debug APK lands at:

```
app/build/outputs/apk/debug/app-debug.apk
```

## Run on a device

With a device or emulator connected and USB debugging enabled:

```bash
adb devices            # confirm the device is listed
./gradlew installDebug
adb shell am start -n com.umbo.cloudplus.debug/com.umbo.cloudplus.MainActivity
```

Note the `.debug` suffix: the debug build type sets
`applicationIdSuffix = ".debug"`, so it installs alongside a release build
instead of overwriting it.

## Project layout

```
cloudPlus/
├── app/
│   ├── build.gradle.kts              # AGP config; no kotlin-android plugin
│   ├── proguard-rules.pro            # R8 rules for release builds
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/umbo/cloudplus/
│       │   ├── MainActivity.kt       # entry point + Compose UI
│       │   └── ui/theme/             # Material 3 theme and typography
│       └── res/                      # strings, themes, adaptive launcher icon
├── gradle/
│   ├── libs.versions.toml            # single source of truth for versions
│   └── wrapper/                      # Gradle wrapper
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
├── requirements.txt
└── README.md
```

## Versions

Versions live in `gradle/libs.versions.toml` so there is one place to change
them. Current set:

- Gradle 9.4.1
- Android Gradle Plugin 9.2.1
- Kotlin 2.2.10 (provided by AGP's built-in Kotlin; not declared in `plugins {}`)
- Compose compiler plugin 2.2.10
- Compose BOM 2026.03.01
- compileSdk 36, minSdk 24, targetSdk 36
- Java 17 source and target compatibility

The Compose compiler plugin version must track the Kotlin version AGP supplies.
If you bump AGP, check its bundled Kotlin version and keep the two in step.

## Notes for future work

- **Do not add `kotlin-android`.** AGP 9 compiles Kotlin already.
- **`kapt` is incompatible** with built-in Kotlin. Use KSP, or
  `com.android.legacy-kapt` as a fallback.
- **No DI yet.** Ktor, a database, and auth are all still to be added. Koin is a
  natural fit alongside Ktor here.
- Release builds enable `isMinifyEnabled` and `isShrinkResources`, so add
  keep rules to `proguard-rules.pro` as reflective libraries come in.
