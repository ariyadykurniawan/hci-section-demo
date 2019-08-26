# HCI Section Test

Sample App for user access level with unit test, Integration Test, and Test Coverage Report

## Requirements

For building and running the application you need:

- [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
- [Maven 3](https://maven.apache.org)
- [MySql](https://www.mysql.com/)
- [Docker](https://docs.docker.com/)

## Running the application locally

There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `de.codecentric.springbootsample.Application` class from your IDE.

Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

```shell
$ mvn spring-boot:run
```

## For Unit Test running
```shell
$ mvn clean test
```

## Docker
```shell
$ docker-compose up
```

## Coverage Test Report
```
target/site/jacoco/index.html
```

## Postman Collection
```
https://www.getpostman.com/collections/a9407a10824b1c55a324
```
