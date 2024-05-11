# Use an official Java runtime as a parent image
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /cv24v1

# Copy the application's jar file into the container
COPY target/cv24v1-0.0.1-SNAPSHOT.war app.war

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Run the jar file
ENTRYPOINT ["java", "-jar", "app.war"]
