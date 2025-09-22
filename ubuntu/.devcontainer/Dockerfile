FROM ubuntu:22.04

RUN apt-get update && apt-get install -y \
    build-essential \
    git \
    curl \
    vim \
    && rm -rf /var/lib/apt/lists/*

RUN useradd -ms /bin/bash vscode
USER vscode
WORKDIR /workspace