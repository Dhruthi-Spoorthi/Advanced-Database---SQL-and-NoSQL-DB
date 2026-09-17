package com.metro.ride.controller;

import com.metro.ride.service.RideService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AnalyticsController {
    private final RideService service;

    public AnalyticsController(RideService service) {
        this.service = service;
    }

    @GetMapping("/analytics/driver-revenue")
    public List<Object> driverRevenue() {
        return service.driverRevenue();
    }

    @GetMapping("/drivers/{driverId}/reviews")
    public List<?> reviews(@PathVariable int driverId) {
        return service.reviewsForDriver(driverId);
    }

    @GetMapping("/health")
    public Map<String, Object> health() {
        return service.health();
    }
}
