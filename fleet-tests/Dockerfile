FROM maven:3.8.6-openjdk-11

RUN mkdir -p /tests

COPY pom.xml /tests/pom.xml
COPY src /tests/src

WORKDIR /tests

RUN mvn clean package -DskipTests

VOLUME /root/.m2

# The command to run
ENTRYPOINT ["mvn"]
CMD ["clean", "test"]