# Use a imagem base do PostgreSQL
FROM postgres:13.2

# Definindo variáveis de ambiente para configuração inicial do PostgreSQL
ENV POSTGRES_DB=newsletter
ENV POSTGRES_USER=root
ENV POSTGRES_PASSWORD=root

# Instalar Python e Psycopg2
RUN apt-get update && apt-get install -y python3 python3-pip
RUN pip3 install psycopg2-binary

# Copiar o arquivo de script SQL para a pasta /docker-entrypoint-initdb.d/ que é executado automaticamente durante o startup do container.
COPY ./init.sql /docker-entrypoint-initdb.d/

# Copiando os assets
COPY ./assets /assets

# Copiando e executando o script de inserção de dados
COPY ./insert_data.py /docker-entrypoint-initdb.d/
RUN chmod +x /docker-entrypoint-initdb.d/insert_data.py
