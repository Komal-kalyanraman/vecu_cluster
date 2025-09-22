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
    && rm -rf /var/lib/apt/lists/*

RUN useradd -ms /bin/bash vscode
WORKDIR /workspace

# Expose VNC port
EXPOSE 5900

# Add startup script to root
COPY start-gui.sh /start-gui.sh
RUN chmod +x /start-gui.sh

USER vscode

# ENTRYPOINT ["/start-gui.sh"]