FROM openjdk:11
RUN mkdir /app
COPY target/*.jar /app/app.jar
EXPOSE 8080
CMD ["java", "-Xlog:gc*", "-Xms1000m", "-jar" , "/app/app.jar"]