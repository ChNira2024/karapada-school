FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/karapada-school-0.0.1-SNAPSHOT.jar karapadaschool.jar
EXPOSE 2424
ENTRYPOINT ["java", "-jar", "karapadaschool.jar"]
