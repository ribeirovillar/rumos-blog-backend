CREATE TABLE posts (
    id UUID PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    created TIMESTAMP NOT NULL,
    author_id UUID NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users (id)
);