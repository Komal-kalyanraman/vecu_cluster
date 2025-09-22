#!/bin/bash
exec > /tmp/start-gui.log 2>&1
set -e

export ANDROID_SDK_ROOT=${ANDROID_SDK_ROOT:-/opt/android-sdk}
export PATH=$PATH:$ANDROID_SDK_ROOT/cmdline-tools/latest/bin:$ANDROID_SDK_ROOT/platform-tools:$ANDROID_SDK_ROOT/emulator

# Clean up any old X server locks
rm -f /tmp/.X0-lock || true

# Start Xvfb if not running
if ! pgrep Xvfb > /dev/null; then
  Xvfb :0 -screen 0 1280x800x16 &
  sleep 2
fi

export DISPLAY=:0

# Start fluxbox if not running
if ! pgrep fluxbox > /dev/null; then
  fluxbox &
fi

# Start x11vnc if not running
if ! pgrep x11vnc > /dev/null; then
  x11vnc -display :0 -forever -nopw -listen 0.0.0.0 -rfbport 5900 &
fi

# --- NEW: Check and create AVD if missing ---
if ! $ANDROID_SDK_ROOT/emulator/emulator -list-avds | grep -q '^dev_avd$'; then
  echo "AVD dev_avd not found, creating..."
  echo "no" | $ANDROID_SDK_ROOT/cmdline-tools/latest/bin/avdmanager create avd -n dev_avd -k "system-images;android-33;google_apis;x86_64" --device "pixel"
fi

# Start Android emulator if not running
if ! pgrep emulator > /dev/null; then
  $ANDROID_SDK_ROOT/emulator/emulator -avd dev_avd -noaudio -no-boot-anim -accel off -gpu swiftshader_indirect -no-snapshot &
fi

# Keep the container running
tail -f /dev/null