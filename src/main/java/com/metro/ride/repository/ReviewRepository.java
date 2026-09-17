package com.metro.ride.repository;

import com.metro.ride.model.Review;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ReviewRepository {

    private final MongoTemplate mongo;

    public ReviewRepository(MongoTemplate mongo) {
        this.mongo = mongo;
    }

    public List<Review> findAll() {
        return mongo.findAll(Review.class, "reviews");
    }

    public List<Review> findByDriver(int driverId) {
        return mongo.query(Review.class)
                .matching(Query.query(Criteria.where("driverId").is(driverId)))
                .all();
    }

    public long count() {
        return mongo.count(
                Query.query(new Criteria()),
                "reviews");
    }

    public long updateComment(int reviewId, String comment) {
        Query query = Query.query(Criteria.where("reviewId").is(reviewId));
        Update update = new Update().set("comment", comment);

        return mongo.updateFirst(query, update, "reviews")
                .getModifiedCount();
    }

    public long delete(int reviewId) {
        Query query = Query.query(Criteria.where("reviewId").is(reviewId));
        return mongo.remove(query, "reviews").getDeletedCount();
    }
}