-- =========================
-- Create Tables
-- =========================

CREATE TABLE departments (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             name VARCHAR(100) NOT NULL,
                             description TEXT
);

CREATE TABLE hospitals (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           name VARCHAR(150) NOT NULL,
                           phone_number VARCHAR(20),
                           website VARCHAR(255),
                           location_id VARCHAR(50) NOT NULL,
                           icus SMALLINT
);

CREATE TABLE hospital_types (
                                hospital_id BIGINT,
                                type VARCHAR(50),
                                FOREIGN KEY (hospital_id) REFERENCES hospitals(id) ON DELETE CASCADE
);

CREATE TABLE doctors (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(100) NOT NULL,
                         phone_number VARCHAR(20),
                         email VARCHAR(100),
                         location_id VARCHAR(50),
                         department_id BIGINT,
                         FOREIGN KEY (department_id) REFERENCES departments(id)
);

CREATE TABLE doctor_specialties (
                                    doctor_id BIGINT,
                                    specialty VARCHAR(100),
                                    FOREIGN KEY (doctor_id) REFERENCES doctors(id) ON DELETE CASCADE
);

CREATE TABLE doctor_hospital (
                                 id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                 doctor_id BIGINT,
                                 hospital_id BIGINT,
                                 appointment_fee DECIMAL(10, 2),
                                 FOREIGN KEY (doctor_id) REFERENCES doctors(id),
                                 FOREIGN KEY (hospital_id) REFERENCES hospitals(id)
);

CREATE TABLE doctor_hospital_appointment_times (
                                                   doctor_hospital_id BIGINT,
                                                   appointment_time TIMESTAMP,
                                                   FOREIGN KEY (doctor_hospital_id) REFERENCES doctor_hospital(id) ON DELETE CASCADE
);

CREATE TABLE doctor_hospital_weekly_schedule (
                                                 doctor_hospital_id BIGINT,
                                                 weekly_schedule VARCHAR(20),
                                                 FOREIGN KEY (doctor_hospital_id) REFERENCES doctor_hospital(id) ON DELETE CASCADE
);

CREATE TABLE hospital_departments (
                                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                      hospital_id BIGINT,
                                      department_id BIGINT,
                                      head_doctor_id BIGINT,
                                      contact_number VARCHAR(20),
                                      beds SMALLINT,
                                      FOREIGN KEY (hospital_id) REFERENCES hospitals(id),
                                      FOREIGN KEY (department_id) REFERENCES departments(id),
                                      FOREIGN KEY (head_doctor_id) REFERENCES doctors(id)
);

CREATE TABLE hospital_department_available_days (
                                                    hospital_department_id BIGINT,
                                                    available_day VARCHAR(20),
                                                    FOREIGN KEY (hospital_department_id) REFERENCES hospital_departments(id) ON DELETE CASCADE
);

-- INSERTING DUMMY DATA

INSERT INTO departments (id, name, description) VALUES
                                                    (1, 'Cardiology', 'Heart related treatments'),
                                                    (2, 'Neurology', 'Brain and nervous system');

INSERT INTO hospitals (id, name, phone_number, website, location_id, icus) VALUES
                                                                               (1, 'Green Life Hospital', '0123456789', 'https://greenlife.com', 'loc1', 10),
                                                                               (2, 'Apollo Hospital', '0987654321', 'https://apollo.com', 'loc2', 15);

INSERT INTO hospital_types (hospital_id, type) VALUES
                                                   (1, 'PRIVATE'),
                                                   (1, 'SPECIALIZED'),
                                                   (2, 'PUBLIC');

INSERT INTO doctors (id, name, phone_number, email, location_id, department_id) VALUES
                                                                                    (1, 'Dr. Ayesha Rahman', '01711112222', 'ayesha@example.com', 'loc3', 1),
                                                                                    (2, 'Dr. Imran Khan', '01888889999', 'imran@example.com', 'loc4', 2);

INSERT INTO doctor_specialties (doctor_id, specialty) VALUES
                                                          (1, 'Cardiologist'),
                                                          (2, 'Neurosurgeon'),
                                                          (2, 'Stroke Specialist');

INSERT INTO doctor_hospital (id, doctor_id, hospital_id, appointment_fee) VALUES
                                                                              (1, 1, 1, 800.00),
                                                                              (2, 2, 2, 1000.00);

INSERT INTO doctor_hospital_appointment_times (doctor_hospital_id, appointment_time) VALUES
                                                                                         (1, '2025-06-01 09:00:00'),
                                                                                         (1, '2025-06-01 10:30:00'),
                                                                                         (2, '2025-06-01 14:00:00');

INSERT INTO doctor_hospital_weekly_schedule (doctor_hospital_id, weekly_schedule) VALUES
                                                                                      (1, 'Sunday'),
                                                                                      (1, 'Tuesday'),
                                                                                      (2, 'Monday'),
                                                                                      (2, 'Thursday');

INSERT INTO hospital_departments (id, hospital_id, department_id, head_doctor_id, contact_number, beds) VALUES
                                                                                                            (1, 1, 1, 1, '01999999999', 25),
                                                                                                            (2, 2, 2, 2, '01812345678', 30);

INSERT INTO hospital_department_available_days (hospital_department_id, available_day) VALUES
                                                                                           (1, 'Monday'),
                                                                                           (1, 'Wednesday'),
                                                                                           (2, 'Tuesday'),
                                                                                           (2, 'Friday');
