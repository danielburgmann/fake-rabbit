# fake-rabbit application endpoints

## Endpoint `/hello`

This endpoint returns a greeting for a given `name` GET-parameter.
Returned formats are

* HTML for `/hello` and `/hello.html`
* JSON for `/hello.json`
* plain text for `/hello.txt`

With the endpoint one can test

* frontend applications doing backend requests
* the handling of parameters by layer 7 load balancer

## Endpoint `/env`

This endpoint returns a listing of

* environment variables
* network interface ips

Returned formats are

* HTML for `/env` and `/env.html`
* plain text for `/env.txt`

With this endpoint one can

* test frontend load balancing between multiple instance by `curl` to the load balancer
* debug environment variables
* find out ip configurations

## Endpoint `/log.html`

This endpoint provides a user interface to write log messages of certain log levels to the application output.
It can be used to provoke log messages to understand log processing for example in kubernetes environment.

## Endpoint `/config.html`

This endpoint shows content from an external configuration file provided to the application.
It can be used to demonstrate the use of kubernetes [configMaps's](https://kubernetes.io/docs/tasks/configure-pod-container/configure-pod-configmap/)
or to show how a file is loaded from a [pod volume](https://kubernetes.io/docs/tasks/configure-pod-container/configure-volume-storage/).

## Endpoint `/buildInfo`

This endpoint demonstrates how build information gathered while building the application can be shown as a JSON endpoint.
