# Taxi Fleet System WIP
Taxi Fleet System oriented to booking rides and managing available cars.

This is a working progress and it is NOT yet finished.

The project includes two different **Spring Boot** services:
- **fleet-booking-service** 
- **fleet-taxi-service**

The services are designed to exchange messages using **RabbitMQ**.

## Build

The project is built as a common **Maven** multi-module project. It can be built using a regular **Maven** build command.

```
mvn clean package
```

### Docker

The project also includes a **docker-compose** file named **_docker-compose.yml_** meant to be used to build the services images and run them using **Docker**.

The file also contains the necessary configuration to run a **RabbitMQ** instance.

## Integration Tests

The project includes a module named **_fleet-tests_** intended to be used for integration tests. The module is not yet implemented, but it already has a Dockerfile to be used with a **docker-compose** extension file named **_docker-compose.tests.yml_**.

These tests are intended to be run inside a **Docker** container using **docker-compose**.
