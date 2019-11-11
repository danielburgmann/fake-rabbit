#!/bin/sh

# basic shell script infos
script_absolute=`cd ${0%/*} && echo $PWD/${0##*/}`
script_name=${script_absolute##*/}
script_dir=${script_absolute%/*}

# import config
source ${script_dir}/config.sh

# settings
temp_dir="${FAKE_RABBIT_SCENARIO_GLOWROOT_TEMP_DIR}"

# check parameter
if [ $# -lt 1 ];
then
    printf 'Usage: %s <port-for-app>\n' "${script_name}";
    exit 1;
fi

# parameters
app_port="${1}"
app_dir="${temp_dir}/apps/${app_port}"

# kill app
app_pid_file="${app_dir}/app.pid"
if [ -f "${app_pid_file}" ];
then
    app_pid=`cat "${app_pid_file}"`
    printf 'Killing app %s with PID %s\n' "${app_dir}" "${app_pid}"
    kill -9 "${app_pid}";
else
    echo "PID file ${app_pid_file} not found, so nothing will be killed.";
fi

# clean up app dir
if [ -d "${app_dir}" ];
then
    echo "Cleaning up ${app_dir} ...";
    rm -rv ${app_dir}
else
    echo "App dir ${app_dir} not found, so nothing will be cleaned up."
fi
