# Using OpenFeign HTTP client builder with spring

[Spring cloud offers an integration](https://spring.io/projects/spring-cloud-openfeign#overview) with
[OpenFeign HTTP client builder](https://github.com/OpenFeign/feign) in Spring boot.

All details on how to use it can be found at the
[Spring Cloud OpenFeign reference](https://cloud.spring.io/spring-cloud-static/spring-cloud-openfeign/2.2.2.RELEASE/reference/html/).

The sample implementation is located in package `openfeign`:

* a github client (taken from the OpenFeign basics) in package `openfeign.github`
* a client to the `/hello.json` endpoint of the fake-rabbit itself (expected to run at `localhost:8080`)
* a simplified controller
* a simplified service

Besides it is necessary to enable the OpenFeign extension with annotation `@EnableFeignClients` in the application class
`FakeRabbitApplication`. 

## Logging

Take note of the logging configuration in `application.properties` and `FeignConfiguration.java` which is 
triggered by setting the general logging level for the OpenFeign clients to `DEBUG` and the special
`feign.Logger` to `FULL`. 
