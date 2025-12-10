CREATE DATABASE IF NOT EXISTS simpledb
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE simpledb;

CREATE TABLE IF NOT EXISTS item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL
);

INSERT INTO item (name) VALUES
('CeraVe Cream'),
('Simple Cleanser'),
('Nivea Soft');
