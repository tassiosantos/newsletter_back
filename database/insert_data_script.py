import json
import psycopg2
import time

# Aguarda o PostgreSQL iniciar
time.sleep(10)

# Conexão com o banco de dados
conn = psycopg2.connect(
    dbname="newsletter",
    user="root",
    password="root",
    host="localhost"
)

# Cursor para executar comandos SQL
cur = conn.cursor()

# Lendo o arquivo JSON
with open('/docker-entrypoint-initdb.d/assets/db.json') as f:
    data = json.load(f)
    news = data['news']

    # Inserindo as notícias no banco de dados
    for new in news:
        # Lendo a imagem como dados binários
        with open(f"/docker-entrypoint-initdb.d/assets/imagens/{new['imagePath']}", 'rb') as image_file:
            image_data = image_file.read()

        cur.execute(
            "INSERT INTO news (id, title, image, summary, content) VALUES (%s, %s, %s, %s, %s)",
            (new['id'], new['title'], psycopg2.Binary(image_data), new['summary'], new['content'])
        )

# Confirmando as operações
conn.commit()

# Fechando a conexão
cur.close()
conn.close()
