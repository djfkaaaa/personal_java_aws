FROM arm64v8/gradle:8.7.0-jdk17 AS builder
COPY --chown=gradle:gradle . /gradle/
WORKDIR /gradle
RUN gradle build --no-daemon

FROM arm64v8/openjdk:17
RUN mkdir /app
COPY --from=builder /gradle/build/libs/*.jar /app/app.jar
ARG PROFILE
ENTRYPOINT ["java", "-jar", "/app/app.jar"]
EXPOSE 8080