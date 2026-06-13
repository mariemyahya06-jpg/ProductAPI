# ===== Stage 1 : build avec Maven (Java 21) =====
FROM maven:3.9-eclipse-temurin-21 AS build
WORKDIR /app
COPY pom.xml .
COPY src ./src
RUN mvn clean package -DskipTests

# ===== Stage 2 : execution (JRE 21 leger) =====
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/target/ProductAPI-0.0.1-SNAPSHOT.jar app.jar

# Render fournit la variable PORT ; l'application la lit via server.port=${PORT:8085}
EXPOSE 8085
ENTRYPOINT ["java", "-jar", "app.jar"]
