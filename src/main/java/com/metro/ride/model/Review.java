package com.metro.ride.model;

import org.springframework.data.mongodb.core.mapping.Field;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;
import java.util.List;

@Document("reviews")
public record Review(
        int reviewId,
        int rideId,
        int customerId,
        int driverId,
        double rating,
        String comment,
        List<String> tags,
        @Field("createdAt") Instant createdAt
) {}
