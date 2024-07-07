CREATE TABLE topics (
    id BIGINT AUTO_INCREMENT NOT NULL PRIMARY KEY,
    title VARCHAR(100) NOT NULL,
    message LONGTEXT NOT NULL,
    creation_date DATE NOT NULL,
    status VARCHAR(100) NOT NULL,
    author_id BIGINT,
    course_id BIGINT,
    FOREIGN KEY (author_id) REFERENCES authors(id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);
