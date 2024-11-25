# Etapa de build
FROM maven:3.9.9-eclipse-temurin-21 AS build
WORKDIR /app

# Copiar o arquivo pom.xml e apenas o diretório src
COPY pom.xml ./
COPY src ./src

# Realizar o build da aplicação
RUN mvn clean package -DskipTests

# Etapa de execução
FROM openjdk:21
WORKDIR /app

# Copiar o JAR gerado na etapa de build
COPY --from=build /app/target/*.jar app.jar

# Expor a porta 8080 (porta padrão do Spring Boot)
EXPOSE 8080

# Comando para iniciar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]
