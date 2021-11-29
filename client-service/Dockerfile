FROM adoptopenjdk/openjdk11:alpine-jre

ARG APP_NAME="client-service"
ARG JAR_FILE="/target/${APP_NAME}.jar"

COPY ${JAR_FILE} client-service-0.0.1.jar
ENTRYPOINT ["java", "-jar", "client-service-0.0.1.jar"]