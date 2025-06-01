-- =========================
-- Create Tables
-- =========================

CREATE TABLE departments (
                             id VARCHAR(50) PRIMARY KEY,
                             name VARCHAR(100) NOT NULL,
                             description TEXT
);

CREATE TABLE hospitals (
                           id VARCHAR(50) PRIMARY KEY,
                           name VARCHAR(150) NOT NULL,
                           phone_number VARCHAR(20),
                           website VARCHAR(255),
                           location_id VARCHAR(50) NOT NULL,
                           icus SMALLINT
);

CREATE TABLE hospital_types (
                                hospital_id VARCHAR(50),
                                type VARCHAR(50),
                                FOREIGN KEY (hospital_id) REFERENCES hospitals(id) ON DELETE CASCADE
);

CREATE TABLE doctors (
                         id VARCHAR(50) PRIMARY KEY,
                         name VARCHAR(100) NOT NULL,
                         phone_number VARCHAR(20),
                         email VARCHAR(100),
                         location_id VARCHAR(50),
                         department_id VARCHAR(50),
                         FOREIGN KEY (department_id) REFERENCES departments(id)
);

CREATE TABLE doctor_specialties (
                                    doctor_id VARCHAR(50),
                                    specialty VARCHAR(100),
                                    FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE
);

CREATE TABLE doctor_hospital (
                                 id VARCHAR(50) PRIMARY KEY,
                                 doctor_id VARCHAR(50),
                                 hospital_id VARCHAR(50),
                                 appointment_fee DECIMAL(10, 2),
                                 FOREIGN KEY (doctor_id) REFERENCES doctors(id),
                                 FOREIGN KEY (hospital_id) REFERENCES hospitals(id)
);

CREATE TABLE doctor_hospital_appointment_times (
                                                   doctor_hospital_id VARCHAR(50),
                                                   appointment_time TIMESTAMP,
                                                   FOREIGN KEY (doctor_hospital_id) REFERENCES doctor_hospital(id) ON DELETE CASCADE
);

CREATE TABLE doctor_hospital_weekly_schedule (
                                                 doctor_hospital_id VARCHAR(50),
                                                 weekly_schedule VARCHAR(20),
                                                 FOREIGN KEY (doctor_hospital_id) REFERENCES doctor_hospital(id) ON DELETE CASCADE
);

CREATE TABLE hospital_departments (
                                      id VARCHAR(50) PRIMARY KEY,
                                      hospital_id VARCHAR(50),
                                      department_id VARCHAR(50),
                                      head_doctor_id VARCHAR(50),
                                      contact_number VARCHAR(20),
                                      beds SMALLINT,
                                      FOREIGN KEY (hospital_id) REFERENCES hospitals(id),
                                      FOREIGN KEY (department_id) REFERENCES departments(id),
                                      FOREIGN KEY (head_doctor_id) REFERENCES doctors(id)
);

CREATE TABLE hospital_department_available_days (
                                                    hospital_department_id VARCHAR(50),
                                                    available_day VARCHAR(20),
                                                    FOREIGN KEY (hospital_department_id) REFERENCES hospital_departments(id) ON DELETE CASCADE
);

-- =========================
-- Insert Dummy Data
-- =========================

-- Departments
INSERT INTO departments (id, name, description) VALUES
                                                    ('dept1', 'Cardiology', 'Heart related treatments'),
                                                    ('dept2', 'Neurology', 'Brain and nervous system');

-- Hospitals
INSERT INTO hospitals (id, name, phone_number, website, location_id, icus) VALUES
                                                                               ('hosp1', 'Green Life Hospital', '0123456789', 'https://greenlife.com', 'loc1', 10),
                                                                               ('hosp2', 'Apollo Hospital', '0987654321', 'https://apollo.com', 'loc2', 15);

-- Hospital types
INSERT INTO hospital_types (hospital_id, type) VALUES
                                                   ('hosp1', 'PRIVATE'),
                                                   ('hosp1', 'SPECIALIZED'),
                                                   ('hosp2', 'PUBLIC');

-- Doctors
INSERT INTO doctors (id, name, phone_number, email, location_id, department_id) VALUES
                                                                                    ('doc1', 'Dr. Ayesha Rahman', '01711112222', 'ayesha@example.com', 'loc3', 'dept1'),
                                                                                    ('doc2', 'Dr. Imran Khan', '01888889999', 'imran@example.com', 'loc4', 'dept2');

-- Doctor specialties
INSERT INTO doctor_specialties (doctor_id, specialty) VALUES
                                                          ('doc1', 'Cardiologist'),
                                                          ('doc2', 'Neurosurgeon'),
                                                          ('doc2', 'Stroke Specialist');

-- Doctor-Hospital Links
INSERT INTO doctor_hospital (id, doctor_id, hospital_id, appointment_fee) VALUES
                                                                              ('dh1', 'doc1', 'hosp1', 800.00),
                                                                              ('dh2', 'doc2', 'hosp2', 1000.00);

-- Doctor-Hospital appointment times
INSERT INTO doctor_hospital_appointment_times (doctor_hospital_id, appointment_time) VALUES
                                                                                         ('dh1', '2025-06-01 09:00:00'),
                                                                                         ('dh1', '2025-06-01 10:30:00'),
                                                                                         ('dh2', '2025-06-01 14:00:00');

-- Weekly schedule
INSERT INTO doctor_hospital_weekly_schedule (doctor_hospital_id, weekly_schedule) VALUES
                                                                                      ('dh1', 'Sunday'),
                                                                                      ('dh1', 'Tuesday'),
                                                                                      ('dh2', 'Monday'),
                                                                                      ('dh2', 'Thursday');

-- Hospital-Department Link
INSERT INTO hospital_departments (id, hospital_id, department_id, head_doctor_id, contact_number, beds) VALUES
                                                                                                            ('hd1', 'hosp1', 'dept1', 'doc1', '01999999999', 25),
                                                                                                            ('hd2', 'hosp2', 'dept2', 'doc2', '01812345678', 30);

-- Available days for departments
INSERT INTO hospital_department_available_days (hospital_department_id, available_day) VALUES
                                                                                           ('hd1', 'Monday'),
                                                                                           ('hd1', 'Wednesday'),
                                                                                           ('hd2', 'Tuesday'),
                                                                                           ('hd2', 'Friday');
