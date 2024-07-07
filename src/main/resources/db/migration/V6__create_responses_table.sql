CREATE TABLE responses (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    message LONGTEXT NOT NULL,
    creation_date DATE NOT NULL,
    author_id BIGINT,
    topic_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES authors(id),
    FOREIGN KEY (topic_id) REFERENCES topics(id)

);