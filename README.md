# Offers API - Spring Boot

## Description

This project aims to provide a REST API for managing product offers based on priorities and deadlines.

## Tools & Technologies Used

- **Spring Boot**: A framework for building stand-alone, production-grade Spring-based applications.
- **JPA (Java Persistence API)**: For database interaction and ORM (Object-Relational Mapping).
- **Lombok**: Used to reduce boilerplate code (generates getters, setters, `toString()`, `equals()`, and `hashCode()`).
- **Spring Data JPA**: To simplify database operations and integrate JPA with Spring.
- **Maven**: Dependency management and build tool.
- **H2**: An in-memory database used for development and testing.
- **MapStruct**: A code generator that simplifies the implementation of mappings between Java bean types based on a convention over configuration approach.
- **Mockito**: A mocking framework for unit tests.
- **JUnit 5**: A testing framework for Java.
- **BDD (Behavior-Driven Development)**: Using Gherkin format for unit tests.
- **Criteria API JPA**: A Java API used to create queries for entities stored in a relational database in a type-safe manner.
- **Swagger OpenAPI 3**: For API documentation and testing.
- **Jacoco**: A Java Code Coverage Library.
- **@Slf4j**: Lombok annotation for logging.
- **Spring Actuator**: To monitor and manage your Spring Boot application.

## Setup & Installation

### Prerequisites

- JDK 17 or later
- Maven (for building and running the project)

### Clone the repository

```bash
git clone git@github.com:DanielLincangoGH/prueba-tecnica-01.git
```
### Build de project

```bash
mvn clean install
```

### Run the application

```bash 
mvn spring-boot:run
``` 
---
## Testing

### Run Unit and Integration Test

```bash 
mvn test
```

---

## Generate Unit and Integration Test Report
### Linux
```bash 
mvn surefire-report:report
xdg-open target/reports/surefire.html
```
### macOS
```bash 
mvn surefire-report:report
open target/reports/surefire.html
```
### Windows
```bash 
mvn surefire-report:report
start target/reports/surefire.html
```

---

## Project Architecture

### Hexagonal Architecture (Ports and Adapters) with the following layers:

- **Application**: Contains business rules of application services.
- **Domain**: Contains the business logic and the domain model.
- **Ports**: Contains the interfaces that define the operations that can be performed.
- **Infrastructure**: Contains the database repositories and the implementation of the domain repositories.
- **Adapter**: Contains the implementation of the ports defined in the domain layer.

```plaintext
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com
│   │   │       └── hiberus
│   │   │           └── hiring
│   │   │               ├── application
│   │   │               │   └── service
│   │   │               │       └── OfferService.java
│   │   │               ├── domain
│   │   │               │   ├── model
│   │   │               │   │   ├── Offer.java
│   │   │               │   │   ├── OfferByPartNumber.java
│   │   │               │   │   └── OfferProduct.java
│   │   │               │   └── ports
│   │   │               │       └── in
│   │   │               │           └── OfferService.java
│   │   │               ├── infrastructure
│   │   │               │   ├── adapters
│   │   │               │   │   └── rest
│   │   │               │   │       └── OfferController.java
│   │   │               │   └── repository
│   │   │               │       └── OfferRepository.java
│   │   │               └── mapper
│   │   │                   └── OfferMapper.java
│   │   └── resources
│   │       └── application.properties
│   │       
│   └── test
│       ├── java
│       │   └── com
│       │       └── hiberus
│       │           └── hiring
│       │               └── OfferServiceTest.java
│       └── resources
│           └── application-test.properties
```

---

## Design Patterns

- **Repository Pattern**: Used to abstract the data access logic and provide a clean API to the rest of the application.
  - **Example**: `OfferRepository` interface in the domain layer and `OfferJpaRepository` class in the infrastructure layer.


- **Builder Pattern**: Used to create complex objects with many parameters.
  - **Example**: Using the `@Builder` annotation from Lombok to create builder methods for entities.


- **DTO (Data Transfer Object) Pattern**: Used to transfer data between the layers of the application.
  - **Example**: `Offer` class as model in the domain layer.


- **Decorator Pattern**: Used to add new functionality to an object without altering its structure.
  - **Example**: `ValidateDateRange` annotation class as custom date range validator.


- **Adapter Pattern**: Used to allow objects with incompatible interfaces to collaborate.
  - **Example**: `OfferMapper` interface in the infrastructure layer and `OfferMapperImpl` as autogenerated MapStruct class.


- **Command Query Responsibility Segregation (CQRS)**: Used to separate the read and write operations of a data store.
  - **Example**: `OfferQueryService` and `OfferCommandService` interfaces in the application layer.

--- 

## Design Patterns for Testing

- **Domain-Driven Design (DDD)**: Used to model the domain of the application in software.
   - **Example**: `Offer` and `Brand` class in the domain layer.


- **Test-Driven Development (TDD)**: Used to write tests before writing the code that is being tested.
    - **Example**: Writing unit tests for the `OfferService` class before implementing the logic.


- **Behavior-Driven Development (BDD)**: Used to write tests in a natural language that describes the behavior of the application.
   - **Example**: Writing Gherkin feature files for the `OfferService` class.

---

## Best Practices

- **Single Responsibility Principle (SRP)**: Used to ensure that a class has only one reason to change.
   - **Examples**: 
     - `OfferService` class for only offers operation.
     - `BrandService` class for only brand operation.
     - `OfferQueryService` class for only query operation.
     - `OfferCommandService` class for only command operation.
     - `OfferRepository` interface for only offer repository operation.
     - `BrandRepository` interface for only brand repository operation.
     - `OfferMapper` interface for only offer mapper operation.
     - `BrandMapper` interface for only brand mapper operation. 


- **Clean Code**: Used to ensure that the code is easy to read, understand, and maintain.
   - **Examples**: 
     - Using meaningful variable names and method names.
     - Writing small methods that do one thing.
     - Avoiding long parameter lists. 
     - Following the Google Checkstyle rules for code formatting.
     - Using the Lombok library to reduce boilerplate code.
     - Using the MapStruct library to simplify mappings between Java bean types.
     - Using the Criteria API JPA to create queries in a type-safe manner.
     - Using the `@Builder` annotation from Lombok to create builder methods for entities.
     - Using the `@Data` annotation from Lombok to generate getters, setters, `toString()`, `equals()`, and `hashCode()` methods.
     - Using the `@AllArgsConstructor` annotation from Lombok to generate a constructor with all arguments.
     - Using the `@NoArgsConstructor` annotation from Lombok to generate a no-args constructor.
     - Using the `@RequiredArgsConstructor` annotation from Lombok to generate a constructor with required arguments.

---

# Database Schema

## H2

We are using H2 as the in-memory database for development and testing.

### Accessing the H2 Console

You can access the H2 console at the following URL:

[http://localhost:8080/h2-console](http://localhost:8080/h2-console)

To open this URL from the command line on Linux, run:

### Open on Linux
```bash 
xdg-open http://localhost:8080/h2-console
```
### Open on macOS
```bash 
open http://localhost:8080/h2-console
```
### Open on Windows
```bash 
start http://localhost:8080/h2-console
```

## Data Dictionary

### Table: brand

| **Field**       | **Data Type**        | **Description**                                             | **Constraints**                                |
|-----------------|----------------------|-------------------------------------------------------------|------------------------------------------------|
| `brand_id`      | `SERIAL`             | Unique identifier for the brand.                            | Primary key, auto-increment.                   |
| `name`          | `VARCHAR(32)`        | Name of the brand.                                          | Not null, maximum 32 characters.               |
| `create_date`   | `TIMESTAMP`          | Creation date of the brand.                                 | Default value: `CURRENT_TIMESTAMP`.            |

### Table: offers

| **Field**       | **Data Type**        | **Description**                                             | **Constraints**                                |
|-----------------|----------------------|-------------------------------------------------------------|------------------------------------------------|
| `offer_id`      | `SERIAL`             | Unique identifier for the offer.                            | Primary key, auto-increment.                   |
| `brand_id`      | `INT`                | Identifier of the brand related to the offer.               | Foreign key, references `brand_id` in **BRAND**.|
| `start_date`    | `TIMESTAMPTZ`        | Start date of the offer in UTC format (ISO8601).            | Not null.                                      |
| `end_date`      | `TIMESTAMPTZ`        | End date of the offer in UTC format (ISO8601).              | Not null.                                      |
| `price_list`    | `NUMERIC`            | Rate or identifier of the price list.                       | Not null.                                      |
| `partnumber`    | `VARCHAR(12)`        | Product code associated with the offer.                     | Not null, maximum 12 characters.               |
| `priority`      | `SMALLINT`           | Priority of the offer within the date range.                | Not null.                                      |
| `currency`      | `Currency`           | Currency of the offer, using an ENUM type (USD or EUR).     | Not null, must be `USD` or `EUR`.              |

## Constraints and Special Types

- **`brand_id` in offers**:
    - Foreign key referencing the `brand_id` column in the **BRAND** table.
    - Ensures that each offer is linked to an existing brand.

- **`currency` in offers**:
    - ENUM type defined with values `USD` and `EUR`, representing the two available currencies for offers.

- **`start_date` and `end_date` in OFFERS**:
    - Use the `TIMESTAMPTZ` type to ensure they are stored in UTC format with the correct time (ISO8601).

- **`price_list`, `partnumber`, `priority`**:
    - Ensure valid values are stored for offers, maintaining data integrity.

---

# API Documentation

The API documentation is generated using Swagger with OpenAPI 3. This allows you to explore and test the API endpoints interactively.

## Accessing the API Documentation

You can access the Swagger UI for the API documentation at the following URLs:

- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **OpenAPI JSON**: [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs)

These URLs provide a user-friendly interface to interact with the API and view the available endpoints, request parameters, and response formats.

### Open on Linux
```bash 
xdg-open http://localhost:8080/swagger-ui.html
```
### Open on macOS
```bash 
open http://localhost:8080/swagger-ui.html
```
### Open on Windows
```bash 
start http://localhost:8080/swagger-ui.html
```

---

# Code Quality

## Test Code Coverage

In this project, we are using the JaCoCo library to measure the code coverage of our unit and integration tests.

### Run Test Code Coverage

### Open report in linux
```bash
mvn clean test jacoco:report
xdg-open target/site/jacoco/index.html
```

### Open report in macOS
```bash
mvn clean test jacoco:report
open target/site/jacoco/index.html
```

### Open report in Windows
```bash
mvn clean test jacoco:report
start target/site/jacoco/index.html
```


## Checkstyle
Google Checkstyle rules are used to enforce code quality and maintainability. The rules are defined in the `google_checks.xml` file.

---

## Automated Test Cases Report

This project includes a Generic Test Case Suite that executes all test cases and generates a comprehensive report. The suite is located in the file `src/test/java/com/hiberus/hiring/GenericUseCaseSuiteTest.java`. By default, it is excluded from regular test runs via mvn test, but can be triggered on demand by running `mvn test -DsuiteTesting=true`.

### Linux
```bash 
mvn test -DsuiteTesting=true
xdg-open target/suite-test-report.html
```
### macOS
```bash 
mvn test -DsuiteTesting=true
open target/suite-test-report.html
```
### Windows
```bash 
mvn test -DsuiteTesting=true
start target/suite-test-report.html
```

---

## Health Check

Enabling the Spring Boot Actuator provides a set of production-ready features to help you monitor and manage your application. The `/actuator/health` endpoint provides basic health information about the application.

### Linux
```bash 
mvn spring-boot:run
xdg-open http://localhost:8080/actuator/health
```
### macOS
```bash 
mvn test -DsuiteTesting=true
open http://localhost:8080/actuator/health
```
### Windows
```bash 
mvn test -DsuiteTesting=true
start http://localhost:8080/actuator/health
```
---

## Docker Containerization

This project includes a Dockerfile that allows you to build a Docker image for the application. You can run the application in a Docker container using the following commands:

## Pre-requisites

- **Docker**: Install Docker on your machine.
- **Docker Compose**: Install Docker Compose on your machine.

### Run the application with Docker Compose

To start the container, use:

```bash
 docker compose up --build -d
```

The app runs on port 8080. You can access the API documentation at [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html).

To stop the container, use:

```bash
docker compose down
```

---

# CI/CD with GitHub Actions

This project uses GitHub Actions for Continuous Integration and Continuous Deployment (CI/CD). The CI/CD pipeline is configured to build the project, run tests, and perform a SonarCloud scan for code quality analysis.

## GitHub Actions Workflow

The GitHub Actions workflow is defined in the `.github/workflows/ci.yaml` file. It includes the following steps:

1. **Check out code**:
    - Checks out the repository code.

2. **Set up JDK 17**:
    - Sets up Java Development Kit (JDK) version 17 to build the project.

3. **Cache Maven dependencies**:
    - Caches Maven dependencies to speed up the build process and avoid downloading dependencies repeatedly.

4. **Build with Maven**:
    - Builds the project using Maven commands (`mvn clean install`).


---

## IntelliJ IDEA Setup

1. **Lombok**: Ensure that the Lombok plugin is installed and enabled in your IDE.
   - For IntelliJ IDEA: Go to `File` > `Settings` > `Plugins`, search for `Lombok`, and install it. Restart the IDE if necessary.
   - Enable annotation processing: Go to `File` > `Settings` > `Build, Execution, Deployment` > `Compiler` > `Annotation Processors` and check `Enable annotation processing`.

2. **Code Formatting**: The code formatting follows the Google Checkstyle rules. Ensure that you have the Checkstyle plugin installed in your IDE and configured to use the `google_checks.xml` file.
   - Download the configuration file from [Google Style Guide](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml).
   - For IntelliJ IDEA: Go to `File` > `Code Style` > `Java`, import the downloaded `intellij-java-google-style.xml` file, and set it as the active code style.
   - Apply the code style by clicking on `Apply`.