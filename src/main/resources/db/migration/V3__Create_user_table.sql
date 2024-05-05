CREATE TABLE users (
    id UUID PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    person_id UUID NOT NULL,
    FOREIGN KEY (person_id) REFERENCES persons (id)
);