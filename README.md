# the fake rabbit

![the fake rabbit](src/main/resources/static/images/fake-rabbit.png "the fake rabbit")

## Scope

This is a demo app for use in development or testing scenarios.

**Do not use this in production environments!**

The application itself provides a set of HTTP endpoints documented in the [endpoints.md](docs/endpoints.md).

## Scenarios

The following scenarios are documented and demonstrated:

* [A set of basic shell scripts to dockerize](scenarios/docker/README.md) the fake-rabbit application.
* [An example of the external configuration](scenarios/external-config/README.md) that is shown under `/config.html`.
* [Application performance management with glowroot](scenarios/glowroot/README.md).
* [A minimal static HTML frontend](scenarios/hello-frontend/README.md) that uses `/hello.txt` endpoint of the application to show CORS.
* [A helm kubernetes deployment](scenarios/helm/README.md) for the app.
* [A plain kubernetes deployment](scenarios/kubernetes/README.md) for the app.
* [How to serve different endpoints on different ports of the application](scenarios/multiple-ports/multiple-ports.md) to allow internal and external application access.

### CORS - Cross Origin Resource Sharing

In `FakeRabbitWebConfig` one can see how to configure [CORS](https://en.wikipedia.org/wiki/Cross-origin_resource_sharing) in Spring Boot globally.

## Build

```sh
./gradlew build
```

## Run

```sh
./gradlew bootRun
```

## Build and run distributable

Build the application with:

```sh
./gradlew bootJar
```

The distributable `fake-rabbit-*.jar` will be created under `build/libs`.

Start the application by running the distributable with `java` e.g.:

```sh
java -jar build/libs/fake-rabbit-0.0.1-SNAPSHOT.jar
```

## See

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Spring Web serving content](https://spring.io/guides/gs/serving-web-content/)
* Created with [Spring Initializr](https://start.spring.io/)
  * Generate a _gradle project_
  * with _Java_
  * and Spring Boot _2.0.4_
  * Group: _de.db8.os.sample_
  * dependencies: _Web_
  * Artifact: _fake-rabbit_
