CREATE TABLE post_comments (
    id UUID PRIMARY KEY,
    content TEXT NOT NULL,
    created TIMESTAMP NOT NULL,
    author_id UUID NOT NULL,
    post_id UUID NOT NULL,
    FOREIGN KEY (author_id) REFERENCES users (id),
    FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE
);