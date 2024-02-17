FROM amazoncorretto:17
ARG JAR_FILE_PATH=build/libs/app.jar
COPY ${JAR_FILE_PATH} app.jar
ENTRYPOINT ["java", "-jar", "-Dspring.profiles.active=prod", "app.jar"]