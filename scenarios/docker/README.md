# Dockerize the fake rabbit

* `Dockerfile` dockerizes the fake-rabbit application together with the [glowroot APM](https://glowroot.org) agent for the [APM scenario](../glowroot/README.md)
* `build.sh` demonstrates the docker command to build a docker image (this script only works inside of `scenarios/docker` because it relies on relative path)
* `run.sh` starts the container previously created with `build.sh`
* `kill.sh` kills the local container started with `run.sh`
* `push.sh` shows how a container image can be pushed to a registry
* `clean.sh` removes local container and image created with `build.sh` and `run.sh`
