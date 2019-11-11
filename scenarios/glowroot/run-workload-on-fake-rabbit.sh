#!/bin/sh

# basic shell script infos
script_absolute=`cd ${0%/*} && printf '%s\n' "$PWD/${0##*/}"`
script_name=${script_absolute##*/}
script_dir=${script_absolute%/*}

# config

export FAKE_RABBIT_SCENARIO_GLOWROOT_HELLO_APP_PORTS=443
export FAKE_RABBIT_SCENARIO_GLOWROOT_WORKLOAD_THREADS=12
export FAKE_RABBIT_SCENARIO_GLOWROOT_NUMBER_OF_REQUESTS=60000
export FAKE_RABBIT_SCENARIO_GLOWROOT_HELLO_APP_BASE_URL=https://dbn-test.pit.sh

echo "# Configuration"
env | grep -e '^FAKE_RABBIT_SCENARIO_GLOWROOT_'

echo "# run workload..."
groovy UrlQueryLoop.groovy

