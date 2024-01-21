FROM openjdk:17-oracle

ARG JAR_FILE=build/libs/lawSearch-0.0.1-SNAPSHOT.jar

ENV JWT_SECRET = ${JWT_SECRET} \
DB_URL = ${DB_URL} \
DB_username = ${DB_username} \
DB_password = ${DB_password}

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["java", "-jar", "/app.jar"]