FROM amazoncorretto:17-alpine-jdk

ARG APP_NAME=golos
ARG APP_VERSION=0.0.1
ARG EXTENSION=jar

ARG JAR_FILE="build/libs/${APP_NAME}-${APP_VERSION}.${EXTENSION}"

COPY ${JAR_FILE} app.jar
ENTRYPOINT ["java","-jar","/app.jar"]