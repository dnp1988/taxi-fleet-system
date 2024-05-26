FROM openjdk:11

# The port your service will listen on
EXPOSE 8082

# Copy the service JAR
COPY target/fleet-taxi-service.jar /fleet-taxi-service.jar

# The command to run
CMD ["java", "-jar", "fleet-taxi-service.jar"]