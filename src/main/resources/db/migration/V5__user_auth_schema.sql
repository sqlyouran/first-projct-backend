-- Create users table
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    nickname VARCHAR(100) NOT NULL,
    avatar_url VARCHAR(500),
    role VARCHAR(20) NOT NULL DEFAULT 'USER',
    reset_token VARCHAR(255),
    reset_token_expiry TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE UNIQUE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_reset_token ON users(reset_token);

-- Migrate mock_users data to users table (preserve IDs)
INSERT INTO users (id, email, password, nickname, avatar_url, role, created_at)
SELECT id,
       CONCAT('user', CAST(id AS VARCHAR), '@example.com'),
       '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy',
       nickname,
       avatar_url,
       'USER',
       created_at
FROM mock_users;

-- Add admin user
INSERT INTO users (id, email, password, nickname, role, created_at)
VALUES (100, 'admin@chinamedguide.com', '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy', 'Admin', 'ADMIN', CURRENT_TIMESTAMP);

-- Update foreign keys: posts
ALTER TABLE posts DROP CONSTRAINT IF EXISTS fk_post_user;
ALTER TABLE posts ADD CONSTRAINT fk_post_user FOREIGN KEY (user_id) REFERENCES users(id);

-- Update foreign keys: comments
ALTER TABLE comments DROP CONSTRAINT IF EXISTS fk_comment_user;
ALTER TABLE comments ADD CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES users(id);

-- Reset sequence to avoid conflict with manually inserted IDs (H2 specific)
ALTER TABLE users ALTER COLUMN id RESTART WITH 200;
