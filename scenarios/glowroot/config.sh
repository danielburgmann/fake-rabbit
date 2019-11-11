#!/bin/sh

# endpoint of Glowroot APM (collector port)
export FAKE_RABBIT_SCENARIO_GLOWROOT_COLLECTOR_URL="http://127.0.0.1:8181"

# the directory where appkications are created by start-app.sh
export FAKE_RABBIT_SCENARIO_GLOWROOT_TEMP_DIR="/tmp"
# start-app.sh uses this URL to download Glowroot java-agent
export FAKE_RABBIT_SCENARIO_GLOWROOT_AGENT_DOWNLOAD_URL="https://search.maven.org/classic/remotecontent?filepath=org/glowroot/glowroot-agent/0.13.5/glowroot-agent-0.13.5.jar"
# name of the fake-rabbit app searched by start-app.sh in ../../build/libs
export FAKE_RABBIT_SCENARIO_GLOWROOT_JAR_BUILD="fake-rabbit-0.0.1-SNAPSHOT.jar"
# Glowroot agent id used by all apps. This will form an app id of format <main-id>::<app-port> in Glowroot
export FAKE_RABBIT_SCENARIO_GLOWROOT_MAIN_AGENT_ID="local::loadtest"

# comma separated list of ports to use.
# this influences how many apps are started by run-workload-on-glowroot.sh
# and what endpoints are checked by UrlQueryLoop.groovy
export FAKE_RABBIT_SCENARIO_GLOWROOT_HELLO_APP_PORTS=10001,10002,10003

# config for UrlQueryLoop.groovy
export FAKE_RABBIT_SCENARIO_GLOWROOT_WORKLOAD_THREADS=12
export FAKE_RABBIT_SCENARIO_GLOWROOT_NUMBER_OF_REQUESTS=60000


