#!/bin/sh

script_name="${0}"

if [ $# -lt 1 ];
then
    printf '%s does a CORS request with Origin: http://example.com\n' "${script_name}"
    printf 'Usage: %s <url-to-request>\n' "${script_name}"
    exit 1
fi

url_to_request="${1}"

# curl
# -v verbose, print out headers
# -L follow redirects
# -H add header

curl -v -L -H 'Origin: http://example.com' "${url_to_request}"
