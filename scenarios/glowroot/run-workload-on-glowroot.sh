#!/bin/sh

# basic shell script infos
script_absolute=`cd ${0%/*} && printf '%s\n' "$PWD/${0##*/}"`
script_name=${script_absolute##*/}
script_dir=${script_absolute%/*}

# import config
source ${script_dir}/config.sh

echo "# Configuration"
env | grep -e '^FAKE_RABBIT_SCENARIO_GLOWROOT_'

echo "# Starting apps ..."
echo "$FAKE_RABBIT_SCENARIO_GLOWROOT_HELLO_APP_PORTS" | tr ',' '\n' | while read port; do
    ./start-app.sh $port;
done

echo "# Listing apps ..."
./list-apps.sh

echo "# run workload..."
groovy UrlQueryLoop.groovy

echo "# Stopping apps ..."
echo "$FAKE_RABBIT_SCENARIO_GLOWROOT_HELLO_APP_PORTS" | tr ',' '\n' | while read port; do
    ./stop-app.sh $port;
done
