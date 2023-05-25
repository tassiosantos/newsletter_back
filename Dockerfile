# Use an OpenJDK 11 base image
FROM maven:3.8.4-openjdk-11 AS builder

# Set the working directory inside the container
WORKDIR /newsletter_back

# Copy the pom.xml to the container
COPY /newsletter_back/pom.xml .

# Download the dependencies defined in the pom.xml
# RUN --mount=type=cache,target=/root/.m2 mvn dependency:go-offline -B
# Baixar as dependências do Maven
RUN mvn dependency:go-offline -B

# Copy the rest of the application source code to the container
COPY /newsletter_back/src ./src

# Build the application
# RUN --mount=type=cache,target=/root/.m2 mvn package -DskipTests
# Compilar o projeto
RUN mvn package -DskipTests

RUN ls -la /newsletter_back/target
# Imagem final para a aplicação backend
FROM openjdk:11-jre-slim

# Copiar o arquivo JAR construído a partir do estágio anterior
COPY --from=builder /newsletter_back/target/main-0.0.1-SNAPSHOT.jar .

# Expose the port on which the Spring Boot application will listen
EXPOSE 8080

# Specify the command to run the application when the container starts
CMD ["java", "-jar", "main-0.0.1-SNAPSHOT.jar"]
