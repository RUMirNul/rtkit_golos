FROM openjdk:17-jdk-slim

ARG APP_NAME=notifier
ARG APP_VERSION=0.0.1
ARG EXTENSION=jar

ARG JAR_FILE="build/libs/${APP_NAME}-${APP_VERSION}.${EXTENSION}"

COPY ${JAR_FILE} ${APP_NAME}.jar
ENTRYPOINT ["java","-jar","/notifier.jar"]