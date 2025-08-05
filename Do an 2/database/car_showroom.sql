-- Car Showroom Management System Database
-- Created: 2024-06-09

-- Create database
CREATE DATABASE IF NOT EXISTS car_showroom CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE car_showroom;

-- Create tables
CREATE TABLE IF NOT EXISTS brands (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    country VARCHAR(50),
    description TEXT,
    logo_url VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS cars (
    id INT PRIMARY KEY AUTO_INCREMENT,
    brand_id INT,
    model VARCHAR(100) NOT NULL,
    year INT NOT NULL,
    color VARCHAR(50),
    engine_type VARCHAR(50),
    transmission VARCHAR(50),
    fuel_type VARCHAR(50),
    mileage DECIMAL(10,2),
    price DECIMAL(15,2) NOT NULL,
    status ENUM('available', 'sold', 'reserved', 'maintenance') DEFAULT 'available',
    image_url VARCHAR(255),
    description TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (brand_id) REFERENCES brands(id)
);

CREATE TABLE IF NOT EXISTS customers (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(20),
    address TEXT,
    id_card VARCHAR(20),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS employees (
    id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(20),
    role ENUM('admin', 'sales', 'accountant', 'manager') NOT NULL,
    status ENUM('active', 'inactive') DEFAULT 'active',
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

CREATE TABLE IF NOT EXISTS invoices (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    employee_id INT,
    car_id INT,
    invoice_number VARCHAR(50) UNIQUE NOT NULL,
    total_amount DECIMAL(15,2) NOT NULL,
    tax_amount DECIMAL(15,2) DEFAULT 0,
    discount_amount DECIMAL(15,2) DEFAULT 0,
    final_amount DECIMAL(15,2) NOT NULL,
    payment_method ENUM('cash', 'card', 'bank_transfer') NOT NULL,
    status ENUM('pending', 'paid', 'cancelled') DEFAULT 'pending',
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (employee_id) REFERENCES employees(id),
    FOREIGN KEY (car_id) REFERENCES cars(id)
);

CREATE TABLE IF NOT EXISTS test_drives (
    id INT PRIMARY KEY AUTO_INCREMENT,
    customer_id INT,
    car_id INT,
    employee_id INT,
    scheduled_date DATETIME NOT NULL,
    duration INT DEFAULT 30, -- minutes
    status ENUM('scheduled', 'completed', 'cancelled') DEFAULT 'scheduled',
    notes TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    FOREIGN KEY (customer_id) REFERENCES customers(id),
    FOREIGN KEY (car_id) REFERENCES cars(id),
    FOREIGN KEY (employee_id) REFERENCES employees(id)
);

-- Insert sample data
INSERT INTO brands (name, country, description) VALUES
('Toyota', 'Japan', 'Leading Japanese automotive manufacturer'),
('Honda', 'Japan', 'Innovative Japanese car company'),
('Ford', 'USA', 'American automotive giant'),
('BMW', 'Germany', 'Luxury German automotive brand'),
('Mercedes-Benz', 'Germany', 'Premium German automotive manufacturer');

INSERT INTO cars (brand_id, model, year, color, engine_type, transmission, fuel_type, mileage, price, status) VALUES
(1, 'Camry', 2023, 'White', '2.5L 4-Cylinder', 'Automatic', 'Gasoline', 15000.00, 850000000, 'available'),
(1, 'Corolla', 2023, 'Black', '1.8L 4-Cylinder', 'CVT', 'Hybrid', 8000.00, 650000000, 'available'),
(2, 'Civic', 2023, 'Blue', '1.5L Turbo', 'CVT', 'Gasoline', 12000.00, 720000000, 'available'),
(3, 'Mustang', 2023, 'Red', '5.0L V8', 'Manual', 'Gasoline', 5000.00, 1200000000, 'available'),
(4, 'X5', 2023, 'Silver', '3.0L 6-Cylinder', 'Automatic', 'Diesel', 8000.00, 2500000000, 'available');

INSERT INTO employees (username, password, name, email, role) VALUES
('admin', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 'Nguyễn Văn A', 'admin@showroom.com', 'admin'),
('sales1', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 'Trần Văn B', 'sales1@showroom.com', 'sales'),
('acc1', '$2a$10$N.zmdr9k7uOCQb376NoUnuTJ8iAt6Z5EHsM8lE9lBOsl7iKTVEFDa', 'Lê Thị C', 'acc1@showroom.com', 'accountant');

INSERT INTO customers (name, email, phone, address) VALUES
('Phạm Văn D', 'pham.d@email.com', '0901234567', '123 Đường ABC, Quận 1, TP.HCM'),
('Nguyễn Thị E', 'nguyen.e@email.com', '0912345678', '456 Đường XYZ, Quận 2, TP.HCM'),
('Trần Văn F', 'tran.f@email.com', '0923456789', '789 Đường DEF, Quận 3, TP.HCM'); 