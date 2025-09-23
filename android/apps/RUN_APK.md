# How to Build and Run the Headless LEDFlash APK

This guide explains how to build and run the headless Android LEDFlash application APK using the Gradle Wrapper and the Android emulator in the dev container.

## Why Headless?

We chose a headless (no-GUI) Android app for this testbench because:
- **Simplicity:** No UI code or resources are needed, making the app easier to maintain and debug.
- **Performance:** Headless apps consume fewer resources, allowing the emulator to run faster and use less memory/CPU.
- **Container Environment:** Since we are running everything inside a container, GUI access is not needed. For our proof-of-concept (POC) and data display requirements, a headless service is sufficient and efficient.

## Prerequisites

- Java JDK 17 or higher (already included in the dev container)
- Android SDK, emulator, and build tools (already included in the dev container)
- The Gradle Wrapper (`./gradlew`) and related files present in the project
- The emulator is started automatically by the container (no need to start it manually)

## Steps to Build and Run the APK

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

4. **Install the APK on the running emulator:**

   ```bash
   adb install -r app/build/outputs/apk/debug/app-debug.apk
   ```

5. **Start the headless LEDFlash app:**

   ```bash
   adb shell am start -n com.example.ledflash/.MainActivity
   ```

6. **View the LED state logs:**

   ```bash
   adb logcat | grep LedFlashService
   ```

   You should see alternating `ledON` and `ledOFF` log messages, similar to:

   ```
   09-23 05:38:56.351  3641  3814 I LedFlashService: ledON
   09-23 05:38:57.362  3641  3814 I LedFlashService: ledOFF
   09-23 05:38:58.454  3641  3814 I LedFlashService: ledON
   09-23 05:38:59.516  3641  3814 I LedFlashService: ledOFF
   ...
   ```

## Optional: Viewing the Emulator via VNC (with Remmina)

If you want to see the Android emulator’s screen (for debugging or visual confirmation), you can connect using the Remmina VNC client. The dev container is set up to start a VNC server automatically.

### Steps to Connect with Remmina

1. **Forward the VNC port:**
   - The VNC server runs on port `5900` inside the container.
   - If using VS Code Remote Containers, forward port `5900` to your host machine.

2. **Open Remmina:**
   - Launch Remmina on your host system.

3. **Create a new VNC connection:**
   - Set the protocol to **VNC**.
   - Set the server address to `localhost:5900` (or the forwarded port).
   - Set the password to `android` (or as specified in your container setup).

4. **Connect:**
   - Click "Connect" in Remmina.
   - You should see the Android emulator’s display. For the headless LEDFlash app, there may be no UI, but you can confirm the emulator is running.

> **Note:** VNC access is optional for headless operation, but useful for troubleshooting emulator startup or for running apps with a UI.

## Notes

- The emulator is started automatically by the dev container (via startup script or Dockerfile). Do **not** start it manually unless you have stopped it.
- The app is headless: it runs as a background service and prints logs, with no UI.
- The Gradle Wrapper (`./gradlew`) will automatically download the correct Gradle version if needed.
- For release builds, use `./gradlew assembleRelease` (additional signing configuration required).

## Troubleshooting

- If you encounter errors, check that all dependencies are installed and that your `AndroidManifest.xml` and `build.gradle` files are correctly configured.
- If you see an error about multiple emulators, ensure only one emulator instance is running.
- For more details, run Gradle with the `--stacktrace` option:

  ```bash
  ./gradlew assembleDebug --stacktrace
  ```