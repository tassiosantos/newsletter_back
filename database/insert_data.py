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
with open('./assets/db.json') as f:
    data = json.load(f)
    news = data['news']

    # Inserindo as notícias no banco de dados
    for new in news:
        cur.execute(
            "INSERT INTO newsletter (id, title, image, summary, content) VALUES (%s, %s, %s, %s, %s)",
            (new['id'], new['title'], f'/assets/imagens/{new["imagePath"]}', new['summary'], new['content'])
        )

# Confirmando as operações
conn.commit()

# Fechando a conexão
cur.close()
conn.close()
