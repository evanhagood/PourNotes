CREATE TABLE drinks (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description VARCHAR(1000) NOT NULL,
    espresso_ml INTEGER,
    milk_ml INTEGER,
    serving_size_ml INTEGER,
    milk_texture VARCHAR(100)
);