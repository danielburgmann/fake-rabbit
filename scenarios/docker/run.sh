#!/bin/sh

docker run --name fake-rabbit-on-8080 -p 8080:8080 -it fake-rabbit
