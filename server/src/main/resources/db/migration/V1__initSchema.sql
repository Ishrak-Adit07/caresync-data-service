-- Department table
CREATE TABLE departments (
                             id BIGINT PRIMARY KEY AUTO_INCREMENT,
                             name VARCHAR(100) NOT NULL,
                             description TEXT
);

-- Doctor table
CREATE TABLE doctors (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(100) NOT NULL,
                         phone_number VARCHAR(50),
                         email VARCHAR(100),
                         department_id BIGINT,
                         CONSTRAINT fk_doctor_department FOREIGN KEY (department_id) REFERENCES departments(id)
);

-- Doctor specialties
CREATE TABLE doctor_specialties (
                                    doctor_id BIGINT NOT NULL,
                                    specialty VARCHAR(100),
                                    CONSTRAINT fk_specialty_doctor FOREIGN KEY (doctor_id) REFERENCES doctors(id)
);

-- DoctorLocation table
CREATE TABLE doctor_locations (
                                  id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                  doctor_id BIGINT UNIQUE,
                                  address TEXT,
                                  thana VARCHAR(100),
                                  po VARCHAR(100),
                                  city VARCHAR(100),
                                  postal_code VARCHAR(20),
                                  CONSTRAINT fk_location_doctor FOREIGN KEY (doctor_id) REFERENCES doctors(id)
);

-- Hospital table
CREATE TABLE hospitals (
                           id BIGINT PRIMARY KEY AUTO_INCREMENT,
                           name VARCHAR(150) NOT NULL,
                           phone_number VARCHAR(20),
                           website VARCHAR(255),
                           icus SMALLINT
);

-- Hospital types
CREATE TABLE hospital_types (
                                hospital_id BIGINT NOT NULL,
                                type VARCHAR(100) NOT NULL,
                                CONSTRAINT fk_type_hospital FOREIGN KEY (hospital_id) REFERENCES hospitals(id)
);

-- HospitalLocation table
CREATE TABLE hospital_locations (
                                    id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                    hospital_id BIGINT UNIQUE,
                                    address TEXT NOT NULL,
                                    thana VARCHAR(100),
                                    po VARCHAR(100) NOT NULL,
                                    city VARCHAR(100) NOT NULL,
                                    postal_code VARCHAR(20) NOT NULL,
                                    CONSTRAINT fk_location_hospital FOREIGN KEY (hospital_id) REFERENCES hospitals(id)
);

-- DoctorHospital table
CREATE TABLE doctor_hospital (
                                 id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                 doctor_id BIGINT,
                                 hospital_id BIGINT,
                                 appointment_fee DECIMAL(10,2),
                                 CONSTRAINT fk_dh_doctor FOREIGN KEY (doctor_id) REFERENCES doctors(id),
                                 CONSTRAINT fk_dh_hospital FOREIGN KEY (hospital_id) REFERENCES hospitals(id)
);

-- DoctorHospital appointment times
CREATE TABLE doctor_hospital_appointment_times (
                                                   doctor_hospital_id BIGINT NOT NULL,
                                                   appointment_time TIMESTAMP,
                                                   CONSTRAINT fk_appointment_dh FOREIGN KEY (doctor_hospital_id) REFERENCES doctor_hospital(id)
);

-- DoctorHospital weekly schedule
CREATE TABLE doctor_hospital_weekly_schedule (
                                                 doctor_hospital_id BIGINT NOT NULL,
                                                 weekly_schedule VARCHAR(50),
                                                 CONSTRAINT fk_schedule_dh FOREIGN KEY (doctor_hospital_id) REFERENCES doctor_hospital(id)
);

-- HospitalDepartment table
CREATE TABLE hospital_departments (
                                      id BIGINT PRIMARY KEY AUTO_INCREMENT,
                                      hospital_id BIGINT,
                                      department_id BIGINT,
                                      head_doctor_id BIGINT,
                                      contact_number VARCHAR(50),
                                      beds SMALLINT,
                                      CONSTRAINT fk_hd_hospital FOREIGN KEY (hospital_id) REFERENCES hospitals(id),
                                      CONSTRAINT fk_hd_department FOREIGN KEY (department_id) REFERENCES departments(id),
                                      CONSTRAINT fk_hd_head_doctor FOREIGN KEY (head_doctor_id) REFERENCES doctors(id)
);

-- HospitalDepartment available days
CREATE TABLE hospital_departments_available_day (
                                                    hospital_department_id BIGINT NOT NULL,
                                                    available_day VARCHAR(20),
                                                    CONSTRAINT fk_avail_hd FOREIGN KEY (hospital_department_id) REFERENCES hospital_departments(id)
);

-- Dummy Data

-- Departments
INSERT INTO departments (name, description) VALUES ('Cardiology', 'Heart and blood vessel related care');
INSERT INTO departments (name, description) VALUES ('Neurology', 'Brain and nervous system');

-- Doctors
INSERT INTO doctors (name, phone_number, email, department_id) VALUES
                                                                   ('Dr. John Doe', '123-456-7890', 'john@example.com', 1),
                                                                   ('Dr. Jane Smith', '987-654-3210', 'jane@example.com', 2);

-- Doctor Specialties
INSERT INTO doctor_specialties (doctor_id, specialty) VALUES
                                                          (1, 'Interventional Cardiology'),
                                                          (1, 'Pediatric Cardiology'),
                                                          (2, 'Neurophysiology');

-- Doctor Locations
INSERT INTO doctor_locations (doctor_id, address, thana, po, city, postal_code) VALUES
                                                                                    (1, '123 Heart Ave', 'Gulshan', 'GPO', 'Dhaka', '1212'),
                                                                                    (2, '456 Brain Blvd', 'Dhanmondi', 'PO Box 123', 'Dhaka', '1209');

-- Hospitals
INSERT INTO hospitals (name, phone_number, website, icus) VALUES
                                                              ('City Hospital', '111-222-3333', 'https://cityhospital.com', 5),
                                                              ('Central Medical', '444-555-6666', 'https://centralmedical.com', 8);

-- Hospital types
INSERT INTO hospital_types (hospital_id, type) VALUES
                                                   (1, 'PRIVATE'),
                                                   (1, 'SPECIALIZED'),
                                                   (2, 'PUBLIC');

-- Hospital Locations
INSERT INTO hospital_locations (hospital_id, address, thana, po, city, postal_code) VALUES
                                                                                        (1, '789 Health Street', 'Banani', 'Banani PO', 'Dhaka', '1213'),
                                                                                        (2, '321 Care Lane', 'Mirpur', 'Mirpur PO', 'Dhaka', '1216');

-- Doctor-Hospital relations
INSERT INTO doctor_hospital (doctor_id, hospital_id, appointment_fee) VALUES
                                                                          (1, 1, 1500.00),
                                                                          (2, 2, 2000.00);

-- Doctor-Hospital appointment times
INSERT INTO doctor_hospital_appointment_times (doctor_hospital_id, appointment_time) VALUES
                                                                                         (1, '2025-06-01 10:00:00'),
                                                                                         (1, '2025-06-01 14:00:00'),
                                                                                         (2, '2025-06-02 09:00:00');

-- Doctor-Hospital weekly schedule
INSERT INTO doctor_hospital_weekly_schedule (doctor_hospital_id, weekly_schedule) VALUES
                                                                                      (1, 'Monday'),
                                                                                      (1, 'Wednesday'),
                                                                                      (2, 'Tuesday');

-- Hospital Departments
INSERT INTO hospital_departments (hospital_id, department_id, head_doctor_id, contact_number, beds) VALUES
                                                                                                        (1, 1, 1, '1231231234', 10),
                                                                                                        (2, 2, 2, '4564564567', 8);

-- Available days for departments
INSERT INTO hospital_departments_available_day (hospital_department_id, available_day) VALUES
                                                                                           (1, 'Sunday'),
                                                                                           (1, 'Tuesday'),
                                                                                           (2, 'Monday');
