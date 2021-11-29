FROM adoptopenjdk/openjdk11:alpine-jre

ARG APP_NAME="order-service"
ARG JAR_FILE="/target/${APP_NAME}.jar"

COPY ${JAR_FILE} order-service-0.0.1.jar
ENTRYPOINT ["java", "-jar", "order-service-0.0.1.jar"]