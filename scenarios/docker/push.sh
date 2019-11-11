#!/bin/sh

# pushing to db8os/fake-rabbit docker registry on docker hub

docker tag fake-rabbit:latest db8os/fake-rabbit:latest
docker push db8os/fake-rabbit:latest
