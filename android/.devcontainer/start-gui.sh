#!/bin/bash
exec > /tmp/start-gui.log 2>&1
set -e

# Clean up any old X server locks
rm -f /tmp/.X0-lock || true

# Start Xvfb if not running
if ! pgrep Xvfb > /dev/null; then
  Xvfb :0 -screen 0 1280x800x16 &
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

# Keep the container running
tail -f /dev/null