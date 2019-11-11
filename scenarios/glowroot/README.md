# Usage scenario with application performance management Glowroot

## What's this

This scenario shows the use of the Glowroot application performance management with a Java web application.

## What is glowroot

See <https://glowroot.org/>

## What's in here

- `./config.sh` contains environment variables used by all scripts
- `./run-workload-on-glowroot.sh` runs the `config.sh`-configured workload against glowroot
- `./run-workload-on-fake-rabbit.sh` runs a workload against an installation of fake-rabbit in `kubernetes/` and therefore the configured glowroot central setup
- `./start-app.sh <port>` starts an instance of fake-rabbit at localhost port
- `./stop-app.sh <port>` stops the application by its process id
- `./list-apps.sh` shows all currently existing app directories
- `./UrlQueryLoop.groovy` helps to create some sample load on running application to induce data in Glowroot
- `docker-compose.yaml` a docker compose file which starts up a Glowroot central instance with a backing Cassandra database

### config.sh

Here you configure all settings used by the other scripts. It contains a number of variables starting with the prefix `FAKE_RABBIT_SCENARIO_GLOWROOT_`. Not every scripts will use all settings.

### run-workload-on-glowroot.sh

Uses `start-app.sh`, `UrlQueryLoop.groovy` and `stop-app.sh` to run a workload against a glowroot instance. Everything is configured in `config.sh`.

### run-workload-on-fake-rabbit.sh

This script presumes that there is a glowroot central installation and a fake-rabbit installation in `kubernetes/`. The fake-rabbit setup is done by Kubernetes resources defined in `kubernetes/`. The script settings are configured directly inside the script. It uses `UrlQueryLoop.groovy` to apply the workload.

### start-app.sh

The `start-app.sh` scripts help to start instances of the fake-rabbit app multiple times with the Glowroot agent set as Java agent. Therefore it creates "app directories" in `/tmp/apps` where it puts:

- configuration for the Glowroot agent in `glowroot.properties`
- a copy of the Glowroot agent jar
- the application log in `app.log`
- the application process id in `app.pid`

When running applications you will find all the stuff created by Glowroot agent in the app directory.

### UrlQueryLoop.groovy

Run this script to do a lot of requests to your apps. You have to edit the parameters in `UrlQueryLoop.groovy` to make the script work.
At least the `ports` variable has to be configured to the ports which you used with `./start-app.sh <port>`.

## Prerequisites

What you need to have installed:

- docker
- docker-compose
- Java
- Groovy

For Java and Groovy you can use <https://sdkman.io/>.

## Links

- <https://glowroot.org/>
- <http://cassandra.apache.org/>
- <https://www.docker.com/products/docker-desktop>
- <https://docs.docker.com/compose/>
