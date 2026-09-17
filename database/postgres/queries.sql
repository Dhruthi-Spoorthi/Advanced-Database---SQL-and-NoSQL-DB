-- Read completed rides with customer and driver information
SELECT r.ride_id, c.full_name AS customer, d.full_name AS driver,
       r.pickup_area, r.drop_area, r.fare
FROM rides r
JOIN customers c ON c.customer_id = r.customer_id
JOIN drivers d ON d.driver_id = r.driver_id
WHERE r.ride_status = 'COMPLETED'
ORDER BY r.fare DESC
LIMIT 10;

-- Revenue by driver
SELECT d.driver_id, d.full_name,
       COUNT(r.ride_id) AS completed_rides,
       SUM(r.fare) AS revenue
FROM drivers d
JOIN rides r ON r.driver_id = d.driver_id
WHERE r.ride_status = 'COMPLETED'
GROUP BY d.driver_id, d.full_name
ORDER BY revenue DESC;

-- Average fare by pickup area
SELECT pickup_area, COUNT(*) AS rides, ROUND(AVG(fare), 2) AS average_fare
FROM rides
GROUP BY pickup_area
ORDER BY average_fare DESC;

-- Update example
UPDATE rides SET ride_status = 'COMPLETED' WHERE ride_id = 1;

-- Delete example
DELETE FROM rides WHERE ride_id = 120;

-- Query plan for the indexed status column
EXPLAIN ANALYZE
SELECT ride_id, fare FROM rides
WHERE ride_status = 'COMPLETED'
ORDER BY booked_at DESC;
