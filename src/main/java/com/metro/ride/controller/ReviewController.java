package com.metro.ride.controller;

import com.metro.ride.service.RideService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final RideService service;

    public ReviewController(RideService service) {
        this.service = service;
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> update(
            @PathVariable int id,
            @RequestBody Map<String, String> body) {

        service.updateReview(id, body.get("comment"));
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable int id) {
        service.deleteReview(id);
        return ResponseEntity.noContent().build();
    }
}