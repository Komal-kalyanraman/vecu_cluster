FROM ubuntu:22.04

ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    wget \
    unzip \
    git \
    curl \
    x11vnc \
    xvfb \
    fluxbox \
    net-tools \
    iproute2 \
    libgl1-mesa-dev \
    libpulse0 \
    libqt5widgets5 \
    libqt5gui5 \
    libqt5core5a \
    nano \
    && rm -rf /var/lib/apt/lists/*

# Install Android SDK command line tools
ENV ANDROID_SDK_ROOT=/opt/android-sdk
RUN mkdir -p $ANDROID_SDK_ROOT/cmdline-tools && \
    wget -q https://dl.google.com/android/repository/commandlinetools-linux-11076708_latest.zip -O /tmp/cmdline-tools.zip && \
    unzip /tmp/cmdline-tools.zip -d $ANDROID_SDK_ROOT/cmdline-tools && \
    mv $ANDROID_SDK_ROOT/cmdline-tools/cmdline-tools $ANDROID_SDK_ROOT/cmdline-tools/latest && \
    rm /tmp/cmdline-tools.zip

ENV PATH=$PATH:$ANDROID_SDK_ROOT/cmdline-tools/latest/bin:$ANDROID_SDK_ROOT/platform-tools:$ANDROID_SDK_ROOT/emulator

# Install SDK packages and system image
RUN yes | $ANDROID_SDK_ROOT/cmdline-tools/latest/bin/sdkmanager --sdk_root=$ANDROID_SDK_ROOT \
    "platform-tools" "platforms;android-33" "build-tools;33.0.2" "emulator" "system-images;android-33;google_apis;x86_64" && \
    yes | $ANDROID_SDK_ROOT/cmdline-tools/latest/bin/sdkmanager --licenses

# Create a default AVD
RUN echo "no" | $ANDROID_SDK_ROOT/cmdline-tools/latest/bin/avdmanager create avd -n dev_avd -k "system-images;android-33;google_apis;x86_64" --device "pixel"

# Set environment variables for all users
RUN echo 'export ANDROID_SDK_ROOT=/opt/android-sdk' >> /etc/profile.d/android.sh && \
    echo 'export PATH=$PATH:/opt/android-sdk/cmdline-tools/latest/bin:/opt/android-sdk/platform-tools:/opt/android-sdk/emulator' >> /etc/profile.d/android.sh

RUN useradd -ms /bin/bash vscode
WORKDIR /workspace

# Expose VNC port
EXPOSE 5900

# Add startup script to root
COPY start-gui.sh /start-gui.sh
RUN chmod +x /start-gui.sh

USER vscode