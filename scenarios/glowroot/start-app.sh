#!/bin/sh

# basic shell script infos
script_absolute=`cd ${0%/*} && printf '%s\n' "$PWD/${0##*/}"`
script_name=${script_absolute##*/}
script_dir=${script_absolute%/*}

# import config
source ${script_dir}/config.sh

# settings
glowroot_agent_url="${FAKE_RABBIT_SCENARIO_GLOWROOT_AGENT_DOWNLOAD_URL}"
glowroot_collector_url="${FAKE_RABBIT_SCENARIO_GLOWROOT_COLLECTOR_URL}"
temp_dir="${FAKE_RABBIT_SCENARIO_GLOWROOT_TEMP_DIR}"
jar_build="${FAKE_RABBIT_SCENARIO_GLOWROOT_JAR_BUILD}"
main_agent_id="${FAKE_RABBIT_SCENARIO_GLOWROOT_MAIN_AGENT_ID}"

# check parameter
if [ $# -lt 1 ];
then
    printf 'Usage: %s <port-for-app>\n' "${script_name}";
    exit 1;
fi

# parameters
app_port="${1}"
app_dir="${temp_dir}/apps/${app_port}"

# ensure app is not already existing
if [ -d "${app_dir}" ];
then
    printf 'App already existing in %s !\n' "${app_dir}";
    exit 2;
fi

# ensure glowroot-agent available
if [ ! -f "${temp_dir}/glowroot-agent.jar" ];
then
    printf 'Downloading glowroot agent to %s/glowroot-agent.jar ...\n' "${temp_dir}"
    curl -o "${temp_dir}/glowroot-agent.jar" "${glowroot_agent_url}";
fi

# ensure app dir
mkdir -p -v "${app_dir}"

# ensure app build existing
jar_build_absolute="${script_dir}/../../build/libs/${jar_build}"
if [ -f "${jar_build_absolute}" ]
then
    printf 'Linking app build\n'
    printf '    %s\n' "${jar_build_absolute}"
    printf '    to app dir\n'
    printf '    %s\n' "${app_dir}/app.jar"
    ln -s "${jar_build_absolute}" "${app_dir}/app.jar"
else
    printf 'Could not find  app build\n'
    printf '    %s\n' "${jar_build_absolute}"
    printf '    Maybe you have to run first ./gradlew bootJar ?\n'
    exit 3
fi

# link glowroot agent to app
printf 'Copying glowroot agent in app dir: '
cp -v "${temp_dir}/glowroot-agent.jar" "${app_dir}/glowroot-agent.jar"

# configure glowroot agent
printf 'Creating glowroot agent configuration: %s\n' "${app_dir}/glowroot.properties"
cat << EOF > "${app_dir}/glowroot.properties"
agent.id=${main_agent_id}::app-${app_port}
collector.address=${glowroot_collector_url}
EOF

# start app
printf 'Starting app ...\n'
printf '    PID file: %s\n' "${app_dir}/app.pid"
printf '    Log file: %s\n' "${app_dir}/app.log"
nohup java \
    -javaagent:${app_dir}/glowroot-agent.jar \
    -jar ${app_dir}/app.jar \
    --server.port=${app_port} \
    > ${app_dir}/app.log 2>&1 &
printf '%s' "${!}" > ${app_dir}/app.pid