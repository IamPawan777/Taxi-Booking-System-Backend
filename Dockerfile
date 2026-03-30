# official base image
FROM eclipse-temurin:21-jre-alpine

# container working directory
WORKDIR /project/app

# copy source code from project to destination container working directory
COPY ./target/quick-trip.jar  /project/app/

EXPOSE 1234

# finally run when the container starts
ENTRYPOINT ["java", "-jar", "quick-trip.jar"]  