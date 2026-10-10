# cloudPlus

An Android application scaffolded with Kotlin and Jetpack Compose.

## 🚀 Tech Stack & Architecture

- **Language:** Kotlin 2.2.10 (built-in AGP support)
- **UI Toolkit:** Jetpack Compose (Material 3)
- **Build System:** Gradle 9.4.1 with Android Gradle Plugin (AGP) 9.2.1
- **Minimum SDK:** API 24 (Android 7.0)
- **Target / Compile SDK:** API 36

> **Important Note on AGP 9+:** This project uses AGP 9.2.1, which features **built-in Kotlin support**. The legacy `org.jetbrains.kotlin.android` plugin is intentionally *not* applied in `build.gradle.kts`. Do not add it, as AGP 9 manages Kotlin compilation natively.

---

## 📋 Prerequisites

Before setting up the project, ensure you have the following installed on your development machine:

1. **JDK 17 (LTS)**: Ensure your `JAVA_HOME` environment variable points to a Java 17 installation.
2. **Android SDK**: Platform 36 and build-tools 36.0.0. Ensure your `ANDROID_HOME` environment variable is configured.
3. **Android Studio** (Koala or newer recommended) or command-line Android command-line tools.

---

## 🛠️ Setup Guide for Team Members

Follow these steps to clone and set up the project on your local machine:

### 1. Clone the Repository
```bash
git clone <your-repository-url>
cd cloudPlus
```

### 2. Configure `local.properties`
Android Studio or Gradle requires a `local.properties` file at the root of the project to locate your local Android SDK.

Create a `local.properties` file in the project root directory:

- **macOS / Linux:**
  ```bash
  echo "sdk.dir=$HOME/Library/Android/sdk" > local.properties
  ```
  *(Or point to your custom Linux Android SDK path, e.g., `/home/username/Android/Sdk`)*

- **Windows:**
  Create a `local.properties` file with:
  ```properties
  sdk.dir=C\:\\Users\\YourUsername\\AppData\\Local\\Android\\Sdk
  ```

### 3. Accept SDK Licenses (if required)
If your SDK licenses are not yet accepted on your machine:
```bash
yes | sdkmanager --licenses
```

---

## 🔨 Building the Project

Always use the included Gradle wrapper (`./gradlew`) rather than a global Gradle installation to ensure version consistency.

### Common Gradle Commands:

- **Build Debug APK:**
  ```bash
  ./gradlew assembleDebug
  ```
- **Build Release APK (Minified):**
  ```bash
  ./gradlew assembleRelease
  ```
- **Clean Build Artifacts:**
  ```bash
  ./gradlew clean
  ```

---

## 📱 Running the App

1. Connect an Android device or start an Android Emulator with USB Debugging enabled.
2. Verify the device is connected:
   ```bash
   adb devices
   ```
3. Build and install the debug build:
   ```bash
   ./gradlew installDebug
   ```
4. Launch the app manually or via adb:
   ```bash
   adb shell am start -n com.umbo.cloudplus.debug/com.umbo.cloudplus.MainActivity
   ```

*Note:* The debug build configuration uses `applicationIdSuffix = ".debug"`, allowing debug and release builds to be installed side-by-side on the same device.

---

## 📂 Project Structure

```
cloudPlus/
├── app/
│   ├── build.gradle.kts          # App-level AGP config (no kotlin-android plugin)
│   ├── proguard-rules.pro        # R8 rules for release minification
│   └── src/main/
│       ├── AndroidManifest.xml
│       ├── java/com/umbo/cloudplus/
│       │   ├── MainActivity.kt   # Entry point & Compose UI root
│       │   └── ui/theme/         # Material 3 color scheme, typography, and theme
│       └── res/                  # App resources, strings, themes, and icons
├── gradle/
│   ├── libs.versions.toml        # Version Catalog (single source of truth for dependencies)
│   └── wrapper/                  # Gradle wrapper binaries
├── build.gradle.kts
├── settings.gradle.kts
├── gradle.properties
└── README.md
```

---

## 📌 Development Guidelines for Contributors

- **Dependencies:** Always add or update library versions in `gradle/libs.versions.toml`.
- **Kotlin & KSP:** AGP 9 built-in Kotlin compilation is active. If annotation processors are needed, use KSP (`com.google.devtools.ksp`) rather than legacy `kapt`.
- **Branching & PRs:** Create feature branches from `main` and submit Pull Requests for review.
