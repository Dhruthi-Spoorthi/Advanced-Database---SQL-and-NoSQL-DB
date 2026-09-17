package com.metro.ride.service;

import com.metro.ride.model.Ride;
import com.metro.ride.repository.RideRepository;
import com.metro.ride.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RideService {

    private final RideRepository rides;
    private final ReviewRepository reviews;

    public RideService(RideRepository rides, ReviewRepository reviews) {
        this.rides = rides;
        this.reviews = reviews;
    }

    public List<Ride> allRides() {
        return rides.findAll();
    }

    public Ride ride(int id) {
        return rides.findById(id);
    }

    public void updateStatus(int id, String status) {
        if (!List.of("COMPLETED", "CANCELLED", "ONGOING").contains(status)) {
            throw new IllegalArgumentException("Invalid ride status");
        }

        if (rides.updateStatus(id, status) == 0) {
            throw new IllegalArgumentException("Ride not found");
        }
    }

    public void deleteRide(int id) {
        if (rides.delete(id) == 0) {
            throw new IllegalArgumentException("Ride not found");
        }
    }

    public List<Object> driverRevenue() {
        return rides.driverRevenue();
    }

    public List<?> reviewsForDriver(int driverId) {
        return reviews.findByDriver(driverId);
    }

    public void updateReview(int reviewId, String comment) {
        if (comment == null || comment.isBlank()) {
            throw new IllegalArgumentException("Review comment cannot be empty");
        }

        if (reviews.updateComment(reviewId, comment) == 0) {
            throw new IllegalArgumentException("Review not found");
        }
    }

    public void deleteReview(int reviewId) {
        if (reviews.delete(reviewId) == 0) {
            throw new IllegalArgumentException("Review not found");
        }
    }

    public Map<String, Object> health() {
        return Map.of(
                "sqlRides", rides.findAll().size(),
                "mongoReviews", reviews.count(),
                "status", "connected");
    }
}