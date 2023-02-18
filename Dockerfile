FROM openjdk:17-jdk-slim-buster
VOLUME /tmp
EXPOSE 8080
ARG JAR_FILE=music-app-57blocks.jar
ADD ${JAR_FILE} music-app-57blocks.jar
ENTRYPOINT ["java","-jar","/music-app-57blocks.jar"]
