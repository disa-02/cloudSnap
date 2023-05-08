FROM openjdk:17-jdk-slim
COPY ./target/gallery-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 3000 
ENTRYPOINT ["java","-jar","/app.jar"]