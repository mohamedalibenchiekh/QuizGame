FROM openjdk:11-jre-slim
WORKDIR /app
COPY target/QuizGame-1.0-SNAPSHOT.jar /app/QuizGame.jar
EXPOSE 50051
EXPOSE 8080
CMD ["java", "-jar", "QuizGame.jar"]