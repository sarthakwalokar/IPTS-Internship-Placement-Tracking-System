-- ============================================
-- Internship & Placement Tracking System
-- Database Schema
-- ============================================

CREATE DATABASE IF NOT EXISTS ipts_db;
USE ipts_db;

-- Admin table (Module 1)
CREATE TABLE admin (
    admin_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,   -- store hashed passwords in production
    full_name VARCHAR(100),
    email VARCHAR(100),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Student table (Module 2)
CREATE TABLE student (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    roll_no VARCHAR(20) NOT NULL UNIQUE,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(100),
    phone VARCHAR(15),
    branch VARCHAR(50),              -- e.g., CSE, IT, ECE
    batch_year INT,                  -- e.g., 2024
    cgpa DECIMAL(4,2),
    resume_link VARCHAR(255),        -- link or path to resume
    is_placed BOOLEAN DEFAULT FALSE, -- flag once student gets placed
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Company table (Module 3)
CREATE TABLE company (
    company_id INT AUTO_INCREMENT PRIMARY KEY,
    company_name VARCHAR(100) NOT NULL,
    industry VARCHAR(100),           -- e.g., IT, Finance, Core
    website VARCHAR(255),
    contact_person VARCHAR(100),
    contact_email VARCHAR(100),
    contact_phone VARCHAR(15),
    address TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Internship table (Module 4)
CREATE TABLE internship (
    internship_id INT AUTO_INCREMENT PRIMARY KEY,
    company_id INT NOT NULL,
    title VARCHAR(100) NOT NULL,     -- e.g., "Web Dev Intern"
    description TEXT,
    stipend DECIMAL(10,2),
    duration_months INT,
    start_date DATE,
    end_date DATE,
    location VARCHAR(100),
    last_apply_date DATE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (company_id) REFERENCES company(company_id) ON DELETE CASCADE
);

-- Student Internship Applications (tracks who applied/got selected)
CREATE TABLE internship_application (
    app_id INT AUTO_INCREMENT PRIMARY KEY,
    internship_id INT NOT NULL,
    student_id INT NOT NULL,
    status ENUM('Applied','Shortlisted','Selected','Rejected') DEFAULT 'Applied',
    applied_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    remarks VARCHAR(255),            -- admin can add notes
    FOREIGN KEY (internship_id) REFERENCES internship(internship_id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE
);

-- Placement table (Module 5)
CREATE TABLE placement (
    placement_id INT AUTO_INCREMENT PRIMARY KEY,
    company_id INT NOT NULL,
    student_id INT NOT NULL,
    job_role VARCHAR(100),
    package_lpa DECIMAL(5,2),        -- CTC in LPA
    offer_date DATE,
    joining_date DATE,
    placement_type ENUM('On-Campus','Off-Campus') DEFAULT 'On-Campus',
    remarks VARCHAR(255),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (company_id) REFERENCES company(company_id) ON DELETE CASCADE,
    FOREIGN KEY (student_id) REFERENCES student(student_id) ON DELETE CASCADE
);

-- ============================================
-- Default admin account (password: admin123)
-- ============================================
INSERT INTO admin (username, password, full_name, email)
VALUES ('admin', 'admin123', 'System Admin', 'admin@college.edu');

-- Sample data for testing
INSERT INTO company (company_name, industry, website, contact_person, contact_email, contact_phone, address)
VALUES 
('TCS', 'IT', 'www.tcs.com', 'HR Manager', 'hr@tcs.com', '9876543210', 'Mumbai'),
('Infosys', 'IT', 'www.infosys.com', 'Recruiter', 'recruit@infosys.com', '9876543211', 'Pune'),
('Wipro', 'IT', 'www.wipro.com', 'HR Head', 'hr@wipro.com', '9876543212', 'Bangalore');

INSERT INTO student (username, password, roll_no, full_name, email, phone, branch, batch_year, cgpa)
VALUES 
('john', 'pass123', 'CSE001', 'John Doe', 'john@college.edu', '9000000001', 'CSE', 2024, 8.5),
('jane', 'pass123', 'CSE002', 'Jane Smith', 'jane@college.edu', '9000000002', 'CSE', 2024, 9.0),
('bob', 'pass123', 'IT001', 'Bob Wilson', 'bob@college.edu', '9000000003', 'IT', 2024, 7.8);
