CREATE DATABASE HardwareDB;

USE HardwareDB;

-- Table: hardware_category
CREATE TABLE hardware_category (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Table: hardware_location
CREATE TABLE hardware_location (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    parent_id INT,
    description TEXT,
    CONSTRAINT fk_parent_location FOREIGN KEY (parent_id) REFERENCES hardware_location (id) ON DELETE SET NULL ON UPDATE CASCADE
);

-- Table: hardware
CREATE TABLE hardware (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category_id INT NOT NULL,
    specification VARCHAR(255),
    description TEXT,
    CONSTRAINT fk_hardware_category FOREIGN KEY (category_id) REFERENCES hardware_category (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Table: hardware_picture
CREATE TABLE hardware_picture (
    id INT AUTO_INCREMENT PRIMARY KEY,
    hardware_id INT NOT NULL,
    picture_url VARCHAR(255),
    CONSTRAINT fk_picture_hardware FOREIGN KEY (hardware_id) REFERENCES hardware (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Table: hardware_document
CREATE TABLE hardware_document (
    id INT AUTO_INCREMENT PRIMARY KEY,
    hardware_id INT NOT NULL,
    type VARCHAR(255) NOT NULL,
    file_url VARCHAR(255),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    CONSTRAINT fk_document_hardware FOREIGN KEY (hardware_id) REFERENCES hardware (id) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Table: inventory
CREATE TABLE inventory (
    id INT AUTO_INCREMENT PRIMARY KEY,
    hardware_id INT NOT NULL,
    location_id INT NOT NULL,
    quantity INT NOT NULL,
    status ENUM('available', 'unavailable') NOT NULL,
    last_inbound TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_inventory_hardware FOREIGN KEY (hardware_id) REFERENCES hardware (id) ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT fk_inventory_location FOREIGN KEY (location_id) REFERENCES hardware_location (id) ON DELETE CASCADE ON UPDATE CASCADE
);