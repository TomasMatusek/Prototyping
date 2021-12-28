FROM amazoncorretto:11-alpine-jdk
WORKDIR /app
COPY target/*.jar backend-server.jar
ENTRYPOINT ["java","-jar","backend-server.jar"]