-- Create inquiries table for hospital inquiry submissions
CREATE TABLE inquiries (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id BIGINT NOT NULL,
    hospital_id BIGINT NOT NULL,
    name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    condition_summary CLOB NOT NULL,
    preferred_date DATE,
    status VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_inquiry_user FOREIGN KEY (user_id) REFERENCES users(id),
    CONSTRAINT fk_inquiry_hospital FOREIGN KEY (hospital_id) REFERENCES hospitals(id)
);
