#!/bin/sh

echo "# killing running container"
docker kill fake-rabbit-on-8080
echo "# removing container"
docker rm fake-rabbit-on-8080
echo "# removing images"
docker rmi fake-rabbit db8os/fake-rabbit
