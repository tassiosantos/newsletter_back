# Utilizamos a imagem base do Maven com o JDK 11
FROM maven:3.8.2-openjdk-11

# Copia o diretório do projeto para dentro do container
COPY ./newsletter_back /usr/src/app

# Define o diretório de trabalho
WORKDIR /usr/src/app

RUN echo 'hosts: files mdns4_minimal [NOTFOUND=return] dns mdns4' >> /etc/nsswitch.conf

# Compila o projeto
# RUN mvn clean install
RUN mvn clean package -DskipTests -q

# Expõe a porta 8080 para que possamos acessar nossa aplicação
EXPOSE 8080

# Executa a aplicação
CMD ["java", "-jar", "target/back-0.0.1-SNAPSHOT.jar"]

