CREATE TABLE author_profiles (
    author_id BIGINT NOT NULL,
    profile_id BIGINT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES authors(id),
    FOREIGN KEY (profile_id) REFERENCES profiles(id),
    PRIMARY KEY (author_id, profile_id)
);