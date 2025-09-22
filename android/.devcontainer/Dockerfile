FROM ubuntu:22.04

RUN apt-get update && apt-get install -y \
    openjdk-17-jdk \
    wget \
    unzip \
    git \
    curl \
    && rm -rf /var/lib/apt/lists/*

RUN useradd -ms /bin/bash vscode
USER vscode
WORKDIR /workspace