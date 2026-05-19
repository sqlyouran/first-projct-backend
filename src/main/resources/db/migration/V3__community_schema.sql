-- Community Board Schema

CREATE TABLE mock_users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nickname VARCHAR(50) NOT NULL,
    avatar_url VARCHAR(500),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE posts (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    title VARCHAR(200) NOT NULL,
    content CLOB NOT NULL,
    like_count INT DEFAULT 0,
    favorite_count INT DEFAULT 0,
    comment_count INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_post_user FOREIGN KEY (user_id) REFERENCES mock_users(id)
);

CREATE TABLE comments (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    parent_id BIGINT,
    content CLOB NOT NULL,
    like_count INT DEFAULT 0,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_comment_post FOREIGN KEY (post_id) REFERENCES posts(id),
    CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES mock_users(id),
    CONSTRAINT fk_comment_parent FOREIGN KEY (parent_id) REFERENCES comments(id)
);

CREATE TABLE post_hospital_tags (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    hospital_id BIGINT NOT NULL,
    CONSTRAINT fk_pht_post FOREIGN KEY (post_id) REFERENCES posts(id),
    CONSTRAINT fk_pht_hospital FOREIGN KEY (hospital_id) REFERENCES hospitals(id),
    CONSTRAINT uq_post_hospital UNIQUE (post_id, hospital_id)
);

CREATE TABLE post_specialty_tags (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    post_id BIGINT NOT NULL,
    specialty_id BIGINT NOT NULL,
    CONSTRAINT fk_pst_post FOREIGN KEY (post_id) REFERENCES posts(id),
    CONSTRAINT fk_pst_specialty FOREIGN KEY (specialty_id) REFERENCES specialties(id),
    CONSTRAINT uq_post_specialty UNIQUE (post_id, specialty_id)
);

CREATE TABLE user_interactions (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    target_type VARCHAR(20) NOT NULL,
    target_id BIGINT NOT NULL,
    type VARCHAR(20) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_user_interaction UNIQUE (user_id, target_type, target_id, type)
);

-- Indexes
CREATE INDEX idx_posts_user ON posts(user_id);
CREATE INDEX idx_posts_created ON posts(created_at DESC);
CREATE INDEX idx_comments_post ON comments(post_id);
CREATE INDEX idx_comments_parent ON comments(parent_id);
CREATE INDEX idx_interactions_target ON user_interactions(target_type, target_id);
CREATE INDEX idx_interactions_user ON user_interactions(user_id);
