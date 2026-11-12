# Software Quality Lab 2: Binary Calculator Web App

A Spring Boot web application that demonstrates a binary-number calculator through HTML pages and HTTP endpoints. The Java sources include the application entry point, binary operations, MVC controllers, API controllers, and controller tests.

## Requirements

- JDK 23 (the Maven compiler level configured by this repository)
- Apache Maven

## Build, test, and run

From the repository root:

```bash
mvn test
mvn package
java -jar target/*.war
```

The application uses Spring Boot and Thymeleaf templates. Consult the controllers for the available page and API routes. This is a course project; review and validate the operations before using it with untrusted input.

## Project layout

```text
src/main/Java/com.ontariotechu.sofe3980U/   Binary model and Spring controllers
src/main/resources/templates/               Web pages
src/test/java/                              Controller tests
pom.xml                                     Maven and Spring Boot configuration
```
