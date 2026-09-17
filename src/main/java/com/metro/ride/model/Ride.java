package com.metro.ride.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record Ride(
        int rideId,
        int customerId,
        int driverId,
        String pickupArea,
        String dropArea,
        String rideStatus,
        BigDecimal distanceKm,
        BigDecimal fare,
        LocalDateTime bookedAt
) {}
