#!/bin/sh

GLOWROOT_VERSION="0.13.5"
GLOWROOT_JAR_URL="https://repo.maven.apache.org/maven2/org/glowroot/glowroot-agent/${GLOWROOT_VERSION}/glowroot-agent-${GLOWROOT_VERSION}.jar"
GLOWROOT_JAR="glowroot-agent-${GLOWROOT_VERSION}.jar"
JAR_FOR_DOCKER="fake-rabbit-0.0.1-SNAPSHOT.jar"
JAR_PATH="../../build/libs/$JAR_FOR_DOCKER"

if [ ! -f "${GLOWROOT_JAR}" ]
then
    printf 'Downloading %s .\n' "${GLOWROOT_JAR_URL}"
    curl "${GLOWROOT_JAR_URL}" --output "${GLOWROOT_JAR}"
fi

if [ -f "${JAR_PATH}" ]
then
    printf 'Copying %s here for use in Dockerfile.\n' "${JAR_PATH}"
    cp -v "${JAR_PATH}" .
else
    printf 'Could not find jar %s to include into docker image. Will stop.\n' "${JAR_PATH}"
    printf 'Maybe you have to run first ./gradlew bootJar ?\n'
    exit 1
fi

docker build --tag fake-rabbit:latest .
