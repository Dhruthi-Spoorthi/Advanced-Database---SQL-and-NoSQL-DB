package com.metro.ride.controller;

import com.metro.ride.model.Ride;
import com.metro.ride.service.RideService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rides")
public class RideController {
    private final RideService service;

    public RideController(RideService service) {
        this.service = service;
    }

    @GetMapping
    public List<Ride> all() {
        return service.allRides();
    }

    @GetMapping("/{id}")
    public Ride one(@PathVariable int id) {
        return service.ride(id);
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<Void> status(@PathVariable int id, @RequestParam String value) {
        service.updateStatus(id, value.toUpperCase());
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.deleteRide(id);
        return ResponseEntity.noContent().build();
    }
}
