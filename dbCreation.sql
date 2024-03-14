CREATE TABLE Person (
    person_id SERIAL PRIMARY KEY,
    pid VARCHAR(50) NOT NULL UNIQUE,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    birth_name VARCHAR(255),
    date_of_birth DATE NOT NULL,
    gender CHAR(1)
);
CREATE TABLE Address (
    address_id SERIAL PRIMARY KEY,
    person_id INTEGER NOT NULL,
    address_line1 VARCHAR(255) NOT NULL,
    address_line2 VARCHAR(255),
    city VARCHAR(100) NOT NULL,
    state VARCHAR(100),
    zip_code VARCHAR(20),
    country VARCHAR(100) NOT NULL,
    address_type VARCHAR(50),
    FOREIGN KEY (person_id) REFERENCES Person(person_id)
);
CREATE TABLE Movements (
    movement_id SERIAL PRIMARY KEY,
    stay_id INTEGER NOT NULL,
    service VARCHAR(255) NOT NULL,
    room VARCHAR(50),
    bed VARCHAR(50),
    movement_date TIMESTAMP NOT NULL,
    FOREIGN KEY (stay_id) REFERENCES Stay(stay_id)
);
CREATE TABLE Stay (
    stay_id SERIAL PRIMARY KEY,
    person_id INTEGER NOT NULL,
    stay_number VARCHAR(50) NOT NULL UNIQUE,
    start_date DATE NOT NULL,
    end_date DATE,
    FOREIGN KEY (person_id) REFERENCES Person(person_id)
);
