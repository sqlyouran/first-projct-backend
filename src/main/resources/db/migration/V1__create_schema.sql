CREATE TABLE specialties (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    name_cn VARCHAR(100),
    description VARCHAR(500),
    icon VARCHAR(50)
);

CREATE TABLE hospitals (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(200) NOT NULL,
    name_cn VARCHAR(200),
    city VARCHAR(100) NOT NULL,
    province VARCHAR(100),
    address VARCHAR(500),
    phone VARCHAR(50),
    website VARCHAR(200),
    description VARCHAR(2000),
    has_international BOOLEAN DEFAULT FALSE,
    image_url VARCHAR(500)
);

CREATE TABLE specialty_rankings (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    specialty_id BIGINT NOT NULL,
    hospital_id BIGINT NOT NULL,
    rank_position INT NOT NULL,
    tier VARCHAR(50),
    ranking_year INT NOT NULL,
    source_name VARCHAR(200),
    CONSTRAINT fk_ranking_specialty FOREIGN KEY (specialty_id) REFERENCES specialties(id),
    CONSTRAINT fk_ranking_hospital FOREIGN KEY (hospital_id) REFERENCES hospitals(id)
);

CREATE INDEX idx_rankings_specialty_year ON specialty_rankings(specialty_id, ranking_year);
CREATE INDEX idx_rankings_hospital ON specialty_rankings(hospital_id);
CREATE INDEX idx_hospitals_city ON hospitals(city);
