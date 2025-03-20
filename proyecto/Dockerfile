# Etapa de compilación (con Maven incluido)
FROM maven:3.9-amazoncorretto-17 AS build
WORKDIR /app
COPY . .  # Copiamos todo para asegurarnos de que src está presente
RUN ls -la  # Lista los archivos en /app para verificar si src existe
RUN mvn clean package -DskipTests


# Etapa final (runtime)

FROM openjdk:17-jdk-slim
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]
