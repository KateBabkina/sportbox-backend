FROM openjdk:17
ADD ./sportbox-backend.jar sportbox-backend.jar
ENTRYPOINT ["java", "-jar", "sportbox-backend.jar"]