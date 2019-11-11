#!/bin/sh

# basic shell script infos
script_absolute=`cd ${0%/*} && echo $PWD/${0##*/}`
script_name=${script_absolute##*/}
script_dir=${script_absolute%/*}

# settings
temp_dir="/tmp"

# list
find "${temp_dir}/apps" -depth 1