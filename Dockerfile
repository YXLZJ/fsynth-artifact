FROM debian:bullseye
LABEL maintainer="Anonymous"
LABEL org.opencontainers.image.authors="Anonymous"
LABEL description="A dockerfile that allows to run the erepair experiments"
LABEL version="1.0"
ENV LANG=en_US.UTF-8 LANGUAGE=en_US:en LC_ALL=en_US.UTF-8 TERM='xterm-256color'
ENV DEBIAN_FRONTEND=noninteractive

RUN apt-get --yes --no-install-recommends update && \
    apt-get --yes --no-install-recommends upgrade && \
    apt-get --yes --no-install-recommends install locales software-properties-common gpg-agent build-essential

RUN sed -i '/en_US.UTF-8/s/^# //g' /etc/locale.gen && locale-gen en_US.UTF-8 && dpkg-reconfigure locales

RUN apt-get --yes --no-install-recommends update && \
    apt-get --yes --no-install-recommends install openjdk-17-jdk git python3 python3-pip python3-setuptools python3-wheel pkg-config curl unzip vim tmux diffutils cmake uuid-dev && \
    update-alternatives --config java && \
    apt-get --yes --no-install-recommends install wget && \
    apt-get --purge remove -y .\*-doc$ && \
    apt-get clean

# Build and install antlr4-cpp-runtime from source
RUN wget https://github.com/antlr/antlr4/archive/refs/tags/4.13.1.zip && \
    unzip 4.13.1.zip && \
    cd antlr4-4.13.1/runtime/Cpp && \
    mkdir build && \
    cd build && \
    cmake .. -DANTLR4_INSTALL=ON && \
    make && \
    make install && \
    cd / && \
    rm -rf antlr4-4.13.1 4.13.1.zip

WORKDIR /home

ENV PYTHONPATH="$PYTHONPATH:."

# Set JAVA_HOME correctly
RUN JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java)))) && \
    echo "export JAVA_HOME=$JAVA_HOME" >> /etc/profile && \
    echo "export PATH=$PATH:$JAVA_HOME/bin" >> /etc/profile

ENV JAVA_HOME=${JAVA_HOME}
ENV PATH=${JAVA_HOME}/bin:${PATH}

# Verify Java installation
RUN java -version

COPY artifacts/libs/gradle-7.3.3 /opt/gradle

ENV GRADLE_HOME=/opt/gradle

ADD artifacts/project /home/project

WORKDIR /home/project

ENV PATH=${PATH}:${GRADLE_HOME}/bin

# Build Project
RUN rm -vrf /home/project/bin && \
    gradle --version && \
    gradle deployJar --stacktrace --info

RUN printf "#!/bin/bash\n\njava -jar /home/project/bin/erepair.jar \$@\n" > /usr/bin/erepair && \
    chmod +x /usr/bin/erepair && \
    mkdir -p /home/repairer

# Clean up build dependencies \
RUN apt-get --purge remove -y curl unzip

RUN java --version && erepair --help

# Copy all artifacts into the repairer directory
COPY artifacts /home/repairer/artifacts

# Build the interpreter subjects
RUN chmod +x /home/repairer/artifacts/build_all.sh && /home/repairer/artifacts/build_all.sh

# Re-deploy jar and build native erepair at the very end
RUN cd /home/project && gradle deployJar --stacktrace --info && \
    g++ -std=c++17 /home/repairer/artifacts/erepair.cpp -o /usr/bin/erepair_cpp

#RUN mv -v /home/project/bin/testfiles /

VOLUME /home/repairer

WORKDIR /home/repairer/artifacts

ENTRYPOINT ["/bin/bash"]
