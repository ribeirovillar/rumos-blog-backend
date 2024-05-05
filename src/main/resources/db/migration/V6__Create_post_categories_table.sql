CREATE TABLE post_categories (
    post_id UUID NOT NULL,
    category VARCHAR(255) NOT NULL,
    PRIMARY KEY (post_id, category),
    FOREIGN KEY (post_id) REFERENCES posts (id)
);