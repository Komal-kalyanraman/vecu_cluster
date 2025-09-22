FROM ubuntu:22.04

RUN apt-get update && apt-get install -y \
    build-essential \
    curl \
    git \
    vim \
    && rm -rf /var/lib/apt/lists/*

# Create a non-root user for VS Code (optional, but recommended)
RUN useradd -ms /bin/bash vscode
USER vscode
WORKDIR /workspace