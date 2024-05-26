FROM openjdk:11

# The port your service will listen on
EXPOSE 8081

# Copy the service JAR
COPY target/fleet-booking-service.jar /fleet-booking-service.jar

# The command to run
CMD ["java", "-jar", "fleet-booking-service.jar"]