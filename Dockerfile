FROM debian:bullseye
LABEL maintainer="Anonymous"
LABEL org.opencontainers.image.authors="Anonymous"
LABEL description="A dockerfile that allows to run the erepair experiments"
LABEL version="1.0"
ENV LANG=en_US.UTF-8 LANGUAGE=en_US:en LC_ALL=en_US.UTF-8 TERM='xterm-256color'
ENV DEBIAN_FRONTEND noninteractive

RUN apt-get --yes --no-install-recommends update && \
    apt-get --yes --no-install-recommends upgrade && \
    apt-get --yes --no-install-recommends install locales software-properties-common gpg-agent build-essential

RUN sed -i '/en_US.UTF-8/s/^# //g' /etc/locale.gen && locale-gen en_US.UTF-8 && dpkg-reconfigure locales

RUN apt-get --yes --no-install-recommends update && \
    apt-get --yes --no-install-recommends install openjdk-17-jdk git python3 python3-pip python3-setuptools python3-wheel pkg-config curl unzip vim tmux diffutils && \
    update-alternatives --config java && \
    apt-get --yes --no-install-recommends install wget && \
    apt-get --purge remove -y .\*-doc$ && \
    apt-get clean

WORKDIR /home

ENV PYTHONPATH "$PYTHONPATH:."

# ENV JAVA_HOME=/usr/lib/jvm/java-17-openjdk-amd64

# Set JAVA_HOME correctly
RUN JAVA_HOME=$(dirname $(dirname $(readlink -f $(which java)))) && \
    echo "export JAVA_HOME=$JAVA_HOME" >> /etc/profile && \
    echo "export PATH=$PATH:$JAVA_HOME/bin" >> /etc/profile

ENV JAVA_HOME $JAVA_HOME
ENV PATH $JAVA_HOME/bin:$PATH

# Verify Java installation
RUN java -version

COPY libs/gradle-7.3.3 /opt/gradle

ENV GRADLE_HOME /opt/gradle

ADD project /home/project

WORKDIR /home/project

ENV PATH $PATH:$GRADLE_HOME/bin

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

#RUN mv -v /home/project/bin/testfiles /

VOLUME /home/repairer

WORKDIR /home/repairer

ENTRYPOINT bash
