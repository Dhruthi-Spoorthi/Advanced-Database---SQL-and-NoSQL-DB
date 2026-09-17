# MongoDB document structures

## vehicles

```json
{
  "vehicleId": 1,
  "registration": "MTR-001",
  "model": "Metro SUV 1",
  "category": "SUV",
  "features": ["AC", "GPS", "Music"],
  "capacity": 5,
  "available": true,
  "serviceAreas": ["Central", "East End", "Airport"]
}
```

## reviews

```json
{
  "reviewId": 1,
  "rideId": 1,
  "customerId": 1,
  "driverId": 8,
  "rating": 4.3,
  "comment": "Ride 1 was reviewed after the trip.",
  "tags": ["service", "journey"],
  "createdAt": "2026-01-04T18:00:00Z"
}
```
