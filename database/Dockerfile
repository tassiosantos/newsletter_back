# Use a imagem base do PostgreSQL
FROM postgres:13.2

# Definindo variáveis de ambiente para configuração inicial do PostgreSQL
# ENV POSTGRES_DB=newsletter
ENV POSTGRES_USER=root
ENV POSTGRES_PASSWORD=root

RUN apt-get update && apt-get install -y --no-install-recommends apt-utils

# Copiar o arquivo de script SQL para a pasta /docker-entrypoint-initdb.d/ que é executado automaticamente durante o startup do container.
COPY ./init.sql /docker-entrypoint-initdb.d/



