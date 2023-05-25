CREATE TABLE news(
    id SERIAL PRIMARY KEY,
    title TEXT NOT NULL,
    image TEXT,
    summary TEXT,
    content TEXT
);