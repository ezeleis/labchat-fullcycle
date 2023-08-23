# Use the official Java base image
FROM openjdk:11-jre-slim

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file built by Maven into the container
COPY .mvn/wrapper/maven-wrapper.jar maven-wrapper.mvn
COPY mvn pom.xml ./

RUN ./mvnw dependency:resolve
COPY src ./src
# Expose the port that your Spring application listens on
EXPOSE 8080

# Command to run your Spring application
CMD ["./mvnw", "spring-boot:run"]
