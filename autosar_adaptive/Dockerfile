FROM ubuntu:22.04

ENV DEBIAN_FRONTEND=noninteractive

# Install dependencies and useful tools
RUN apt-get update && \
    apt-get install -y \
        build-essential \
        cmake \
        g++ \
        git \
        nano \
        net-tools \
        iproute2 \
        iputils-ping \
        tcpdump \
        socat \
        netcat \
        lsof \
        strace \
        gdb \
        htop \
        curl \
        tzdata

# Set timezone non-interactively after tzdata is installed
RUN ln -fs /usr/share/zoneinfo/Etc/UTC /etc/localtime && dpkg-reconfigure -f noninteractive tzdata

WORKDIR /workspace

# (Add AUTOSAR Adaptive stack or mockup install here as needed)

CMD ["/bin/bash"]