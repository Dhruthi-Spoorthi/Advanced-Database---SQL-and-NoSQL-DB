DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS rides;
DROP TABLE IF EXISTS drivers;
DROP TABLE IF EXISTS customers;

CREATE TABLE customers (
    customer_id SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    phone VARCHAR(20) NOT NULL UNIQUE,
    created_at DATE NOT NULL
);

CREATE TABLE drivers (
    driver_id SERIAL PRIMARY KEY,
    full_name VARCHAR(100) NOT NULL,
    phone VARCHAR(20) NOT NULL UNIQUE,
    license_number VARCHAR(40) NOT NULL UNIQUE,
    joined_at DATE NOT NULL,
    rating NUMERIC(3,2) NOT NULL CHECK (rating BETWEEN 0 AND 5)
);

CREATE TABLE rides (
    ride_id SERIAL PRIMARY KEY,
    customer_id INT NOT NULL REFERENCES customers(customer_id),
    driver_id INT NOT NULL REFERENCES drivers(driver_id),
    pickup_area VARCHAR(100) NOT NULL,
    drop_area VARCHAR(100) NOT NULL,
    ride_status VARCHAR(20) NOT NULL CHECK (ride_status IN ('COMPLETED','CANCELLED','ONGOING')),
    distance_km NUMERIC(6,2) NOT NULL CHECK (distance_km > 0),
    fare NUMERIC(10,2) NOT NULL CHECK (fare >= 0),
    booked_at TIMESTAMP NOT NULL
);

CREATE TABLE payments (
    payment_id SERIAL PRIMARY KEY,
    ride_id INT NOT NULL UNIQUE REFERENCES rides(ride_id),
    payment_method VARCHAR(20) NOT NULL CHECK (payment_method IN ('CARD','UPI','CASH','WALLET')),
    amount NUMERIC(10,2) NOT NULL CHECK (amount >= 0),
    payment_status VARCHAR(20) NOT NULL CHECK (payment_status IN ('PAID','PENDING','REFUNDED')),
    paid_at TIMESTAMP
);

CREATE INDEX idx_rides_customer ON rides(customer_id);
CREATE INDEX idx_rides_driver ON rides(driver_id);
CREATE INDEX idx_rides_status ON rides(ride_status);
CREATE INDEX idx_rides_booked_at ON rides(booked_at);
CREATE INDEX idx_payments_status ON payments(payment_status);
