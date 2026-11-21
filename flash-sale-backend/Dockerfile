FROM eclipse-temurin:21.0.13_9-jdk AS build
WORKDIR /app
COPY mvnw .
COPY .mvn .mvn
COPY pom.xml pom.xml
RUN ./mvnw dependency:go-offline
COPY src src
RUN ./mvnw package -DskipTests

FROM eclipse-temurin:21.0.13_9-jre AS run
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]
