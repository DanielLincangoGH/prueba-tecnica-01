# Offers API - Spring Boot

## Description

This is a Spring Boot project designed to provide a basic foundation for building RESTful services. It includes integrations with JPA, Lombok, and PostgreSQL.

## Tools & Technologies Used

- **Spring Boot**: A framework for building stand-alone, production-grade Spring-based applications.
- **JPA (Java Persistence API)**: For database interaction and ORM (Object-Relational Mapping).
- **Lombok**: Used to reduce boilerplate code (generates getters, setters, `toString()`, `equals()`, and `hashCode()`).
- **Spring Data JPA**: To simplify database operations and integrate JPA with Spring.
- **Maven**: Dependency management and build tool.
- **H2**: An in-memory database used for development and testing.
- **MapStruct**: A code generator that simplifies the implementation of mappings between Java bean types based on a convention over configuration approach.

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
### Additional Setup

### Additional Setup

1. **Lombok**: Ensure that the Lombok plugin is installed and enabled in your IDE.
   - For IntelliJ IDEA: Go to `File` > `Settings` > `Plugins`, search for `Lombok`, and install it. Restart the IDE if necessary.
   - Enable annotation processing: Go to `File` > `Settings` > `Build, Execution, Deployment` > `Compiler` > `Annotation Processors` and check `Enable annotation processing`.

2. **Code Formatting**: The code formatting follows the Google Checkstyle rules. Ensure that you have the Checkstyle plugin installed in your IDE and configured to use the `google_checks.xml` file.
   - Download the configuration file from [Google Style Guide](https://github.com/google/styleguide/blob/gh-pages/intellij-java-google-style.xml).
   - For IntelliJ IDEA: Go to `File` > `Code Style` > `Java`, import the downloaded `intellij-java-google-style.xml` file, and set it as the active code style.
   - Apply the code style by clicking on `Apply`.