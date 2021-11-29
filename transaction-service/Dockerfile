FROM adoptopenjdk/openjdk11:alpine-jre

ARG APP_NAME="transaction-service"
ARG JAR_FILE="/target/${APP_NAME}.jar"

COPY ${JAR_FILE} transaction-service-0.0.1.jar
ENTRYPOINT ["java", "-jar", "transaction-service-0.0.1.jar"]