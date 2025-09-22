# How to Build the APK

This guide explains how to build the Android application APK using the Gradle Wrapper.

## Prerequisites

- Java JDK 17 or higher installed (already included in the dev container)
- Android SDK and build tools installed (already included in the dev container)
- The Gradle Wrapper (`./gradlew`) and related files present in the project

## Steps to Build the APK

1. **Open a terminal in the project directory:**

   ```bash
   cd /workspace/apps/ledflash
   ```

2. **Build the debug APK:**

   ```bash
   ./gradlew assembleDebug
   ```

3. **Locate the generated APK:**

   After the build completes, the APK will be located at:

   ```
   app/build/outputs/apk/debug/app-debug.apk
   ```

## Notes

- The Gradle Wrapper (`./gradlew`) will automatically download the correct Gradle version if needed.
- You do not need to install Gradle globally.
- For release builds, use `./gradlew assembleRelease` (additional signing configuration required).

## Troubleshooting

- If you encounter errors, check that all dependencies are installed and that your `AndroidManifest.xml` and `build.gradle` files are correctly configured.
- For more details, run Gradle with the `--stacktrace` option:

  ```bash
  ./gradlew assembleDebug --stacktrace
  ```

---